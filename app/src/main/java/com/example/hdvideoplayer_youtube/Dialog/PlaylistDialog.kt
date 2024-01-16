package com.example.hdvideoplayer_youtube.Dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hdvideoplayer_youtube.Activity.Application
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.VideoPlayActivity
import com.example.hdvideoplayer_youtube.Adapter.FolderVideoAdapter
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File

class PlaylistDialog(
    var currentVideoList: ArrayList<Video>
) : BottomSheetDialogFragment() {

    var DeletePosition: Int = 0
    var VideoPosition: Int = 0
    lateinit var videoUri: Uri
    lateinit var folderVideoAdapter: FolderVideoAdapter
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var recyclerView: RecyclerView
    lateinit var folder: TextView
    lateinit var txtEmpty: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.activity_playlist_dialog, null)
        bottomSheetDialog.setContentView(view)

        recyclerView = view.findViewById(R.id.playlist_rv)
        folder = view.findViewById(R.id.playlist_name)
        txtEmpty = view.findViewById(R.id.txtEmpty)
        if (currentVideoList.size == 0) {
            txtEmpty.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            txtEmpty.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
        val folderName = Application.getString("playlistFOlderName")
        folder.text = folderName
        folderVideoAdapter = FolderVideoAdapter(requireActivity(),
            currentVideoList,
            onClick = { title, uri, position ->

//                Position = position

                val intent = Intent(requireContext(), VideoPlayActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("video_title", title)
                intent.putExtra("video_from", "Folder")
                startActivity(intent)
                requireActivity().finish()
            },
            onClickDelete = { it, pos ->

                videoUri = it
                DeletePosition = pos

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    requireContext().contentResolver.delete(videoUri, null, null)
                    currentVideoList.removeAt(DeletePosition)
                    folderVideoAdapter.notifyItemRemoved(DeletePosition)
                    Toast.makeText(requireContext(), "Video Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        deleteImageAPI29(it)
                    } catch (e: Exception) {
                        "error = ${e.message}".log()
                    }
                }


            })
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = folderVideoAdapter
        folderVideoAdapter.notifyDataSetChanged()
        return bottomSheetDialog
    }

    fun deleteImageAPI29(uri: Uri?) {
        val resolver = requireContext().contentResolver
        try {
            resolver.delete(uri!!, null, null)
        } catch (securityException: SecurityException) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val recoverableSecurityException = securityException as RecoverableSecurityException
                val senderRequest = IntentSenderRequest.Builder(
                    recoverableSecurityException.userAction.actionIntent.intentSender
                ).build()
                deleteResultLauncher.launch(senderRequest)
            }
        }
    }

    var deleteResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result != null) {
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val deleted = deleteMediaFile(videoUri, requireContext().contentResolver)
                    if (deleted) {
                        Toast.makeText(context, "Video deleted.", Toast.LENGTH_SHORT).show()
//                        if (!File(currentVideoList[VideoPosition].videoPath).exists()) {
//                            requireActivity().finish()
//                            "here Not exist".log()
//                        } else {
//                            "here exist".log()
//                        }
                        currentVideoList.removeAt(DeletePosition)
                        folderVideoAdapter.notifyItemRemoved(DeletePosition)

                        if (currentVideoList.size == 0) {
                            txtEmpty.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                            "Empty".log()
                        } else {
                            "not Empty".log()
                            txtEmpty.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                        }

                    } else {
                        Toast.makeText(context, "Video Not deleted.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    fun deleteMediaFile(contentUri: Uri, contentResolver: ContentResolver): Boolean {
        return try {
            val deletedRows = contentResolver.delete(contentUri, null, null)
            deletedRows > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}