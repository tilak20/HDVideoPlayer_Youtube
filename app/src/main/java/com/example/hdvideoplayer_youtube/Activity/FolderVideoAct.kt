package com.example.hdvideoplayer_youtube.Activity

import android.Manifest
import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.currentVideoList
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.MainActivity.Companion.folderList
import com.example.hdvideoplayer_youtube.Adapter.FolderVideoAdapter
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityFolderVideoBinding
import com.google.ads.sdk.AdsManager
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

class FolderVideoAct : BaseAct<ActivityFolderVideoBinding>() {

    var deletepos: Int = 0
    var position: Int = 0
    lateinit var videoUri: Uri
    lateinit var folderVideoAdapter: FolderVideoAdapter
    val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityFolderVideoBinding.inflate(layoutInflater)

    override fun initUI() {

        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        position = intent.getIntExtra("position", 0)
        val folder_name = intent.getStringExtra("folderName")

        Application.putString("playlistFOlderName", folder_name.toString())

        binding.apply {

            if (folderList.size != 0) {

                currentVideoList = getAllPlayableVideos(folderList[position].videoBucketId)

                txtNotFound.visibility = View.GONE
                rcView.visibility = View.VISIBLE
                setRV(currentVideoList)
                materialTextView2.text = folderList[position].videoBucket
            } else {
                if (currentVideoList.size == 0) {
                    binding.txtNotFound.visibility = View.VISIBLE
                    binding.rcView.visibility = View.GONE
                } else {
                    binding.txtNotFound.visibility = View.GONE
                    binding.rcView.visibility = View.VISIBLE
                }
            }

            shapeableImageView2.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@FolderVideoAct) { finish() }
            }
        }
    }

    fun setRV(folderList: ArrayList<Video>) {
        folderVideoAdapter =
            FolderVideoAdapter(this, folderList, onClick = { title, uri, position ->

                val rTempMap: MutableMap<String?, Video> =
                    Application.getRecentMap().toMutableMap()
                rTempMap[currentVideoList[position].videoUri] = currentVideoList[position]
                Application.setRecentMap(rTempMap)

                val intent = Intent(this@FolderVideoAct, VideoPlayActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("video_title", title)
                intent.putExtra("video_from", "Folder")
                startActivity(intent)

            }, onClickDelete = { it, pos ->
                videoUri = it
                deletepos = pos

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    contentResolver.delete(videoUri, null, null)
                    currentVideoList.removeAt(deletepos)
                    currentVideoList =
                        getAllPlayableVideos(MainActivity.folderList[position].videoBucketId)
                    setRV(currentVideoList)
                    folderVideoAdapter.notifyItemRemoved(deletepos)
                    "position == ${deletepos}".log()
                    ("Video Deleted").tos()
                } else {
                    try {
                        deleteImageAPI29(it)
                    } catch (e: Exception) {
                        "error = ${e.message}".log()
                    }
                }
            })

//        val scaleInAnimationAdapter = ScaleInAnimationAdapter(folderVideoAdapter)
//        scaleInAnimationAdapter.setDuration(500)
//        scaleInAnimationAdapter.setInterpolator(OvershootInterpolator())
//        scaleInAnimationAdapter.setFirstOnly(false)
        binding.rcView.adapter = folderVideoAdapter
    }


    fun deleteImageAPI29(uri: Uri?) {
        try {
            contentResolver.delete(uri!!, null, null)
        } catch (securityException: SecurityException) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
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
                if (result.resultCode == RESULT_OK) {
                    val deleted = deleteMediaFile(videoUri, contentResolver)
                    if (deleted) {
                        ("Video deleted.").tos()
                        currentVideoList = getAllPlayableVideos(folderList[position].videoBucketId)
                        currentVideoList.removeAt(deletepos)

                        if (currentVideoList.size == 0) {
                            "Empty".log()
                            binding.txtNotFound.visibility = View.VISIBLE
                            binding.rcView.visibility = View.GONE
                        } else {
                            binding.txtNotFound.visibility = View.GONE
                            binding.rcView.visibility = View.VISIBLE
                            "not Empty".log()
                        }
                        folderVideoAdapter.notifyDataSetChanged()
                        folderVideoAdapter.notifyItemRemoved(deletepos)
                    } else {
                        ("Video deleted Not.").tos()
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

    override fun onResume() {
        super.onResume()
        if (currentVideoList.size == 0) {
            binding.txtNotFound.visibility = View.VISIBLE
            binding.rcView.visibility = View.GONE
        } else {
            currentVideoList = getAllPlayableVideos(folderList[position].videoBucketId)
            setRV(currentVideoList)
            binding.txtNotFound.visibility = View.GONE
            binding.rcView.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@FolderVideoAct) { finish() }

    }

}