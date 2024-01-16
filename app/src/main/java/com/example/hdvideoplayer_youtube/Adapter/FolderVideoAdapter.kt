package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.ContentUris
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import android.text.TextUtils
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.convertUnixTimestampToDate
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.getVideoDuration
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.FoldervideoitemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File


class FolderVideoAdapter(
    var folderVideoAct: Activity,
    var folderList: ArrayList<Video>,
    var onClick: (String, String, Int) -> Unit,
    var onClickDelete: (Uri,Int) -> Unit
) : RecyclerView.Adapter<FolderVideoAdapter.ViewData>() {

    lateinit var bottomSheetDialog: BottomSheetDialog

    class ViewData(var binding: FoldervideoitemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        FoldervideoitemBinding.inflate(LayoutInflater.from(folderVideoAct))
    )

    override fun getItemCount() = folderList.size

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {
            shapeableImageView.load(folderList[position].videoPath)
            videoFileName.text = folderList[position].videoTitle

            txtModiFied.text =
                convertUnixTimestampToDate(folderList[position].videoDateadded.toLong())
            txtDuration.text = getVideoDuration(folderList[position].videoDuration)
            txtDuration.setTextColor(
                ContextCompat.getColor(
                    folderVideoAct, R.color.md_theme_light_onSurfaceVariant
                )
            )
            val milliSeconds: Double = folderList[position].videoDuration.toDouble()

//            ("Name == ${folderList[position].videoTitle}").log()

            imgMore.setOnClickListener {
                bottomSheetDialog = BottomSheetDialog(folderVideoAct, R.style.BottomSheetdialogTheme)
                val bsView = LayoutInflater.from(folderVideoAct).inflate(
                    R.layout.video_bs_layout, it.findViewById(R.id.bottom_sheet)
                )

                bsView.findViewById<Group>(R.id.bs_play).setAllOnClickListener {
                    holder.itemView.performClick()
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_rename).setAllOnClickListener(View.OnClickListener {
                    val alertDialog = AlertDialog.Builder(folderVideoAct)
                    alertDialog.setTitle("Rename to")
                    val editText = EditText(folderVideoAct)
                    val path: String = folderList[position].videoPath
                    val file = File(path)
                    var videoName = file.name
                    videoName = videoName.substring(0, videoName.lastIndexOf("."))
                    editText.setText(videoName)
                    alertDialog.setView(editText)
                    editText.requestFocus()
                    alertDialog.setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialog, which ->
                            if (TextUtils.isEmpty(editText.text.toString())) {
                                Toast.makeText(
                                    folderVideoAct, "Can't rename empty file", Toast.LENGTH_SHORT
                                ).show()
                                return@OnClickListener
                            }
                            val onlyPath = file.parentFile!!.absolutePath
                            var ext = file.absolutePath
                            ext = ext.substring(ext.lastIndexOf("."))
                            val newPath = onlyPath + "/" + editText.text.toString() + ext
                            val newFile = File(newPath)
                            val rename = file.renameTo(newFile)
                            if (rename) {
                                val resolver: ContentResolver =
                                    folderVideoAct.applicationContext.contentResolver
                                resolver.delete(
                                    MediaStore.Files.getContentUri("external"),
                                    MediaStore.MediaColumns.DATA + "=?",
                                    arrayOf(file.absolutePath)
                                )
                                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                                intent.data = Uri.fromFile(newFile)
                                folderVideoAct.applicationContext.sendBroadcast(intent)
                                notifyDataSetChanged()
                                Toast.makeText(
                                    folderVideoAct, "Video Renamed", Toast.LENGTH_SHORT
                                ).show()
                                SystemClock.sleep(200)
                                folderVideoAct.recreate()
                            } else {
                                Toast.makeText(
                                    folderVideoAct, "Process Failed", Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    alertDialog.setNegativeButton(
                        "Cancel"
                    ) { dialog, which -> dialog.dismiss() }
                    alertDialog.create().show()
                    bottomSheetDialog.dismiss()
                })
                bsView.findViewById<Group>(R.id.bs_share).setAllOnClickListener {
                    val uri = Uri.parse(folderList[position].videoPath)
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "video/*"
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                    folderVideoAct.startActivity(
                        Intent.createChooser(
                            shareIntent, "Share Video via"
                        )
                    )
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_delete).setAllOnClickListener {
                    val alertDialog = AlertDialog.Builder(folderVideoAct)
                    alertDialog.setTitle("Delete")
                    alertDialog.setMessage("Do you want to delete this video")
                    alertDialog.setPositiveButton(
                        "Delete"
                    ) { dialog, which ->
                        val contentUri = ContentUris.withAppendedId(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            folderList[position].videoId
                        )

                        onClickDelete.invoke(folderList[position].videoUri.toUri(),position)

//                        deleteVideo(folderList[position].videoPath,position)
//                        val isDeleted = deleteFile(File(folderList[position].videoPath))

//                        if (isDeleted) {
//                            ("Video file deleted successfully.").log()
//                        } else {
//                            ("Failed to delete video file.").log()
//                        }

//                        val delete = File(folderList[position].videoPath).delete()
//                        if (delete) {
//                            folderVideoAct.contentResolver.delete(contentUri, null, null)
//                            folderList.removeAt(position)
//                            notifyItemRemoved(position)
//                            notifyItemRangeChanged(position, folderList.size)
//                            Toast.makeText(folderVideoAct, "Video Deleted", Toast.LENGTH_SHORT)
//                                .show()
//                        } else {
//                            Toast.makeText(folderVideoAct, "can't deleted", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                    }
                    alertDialog.setNegativeButton(
                        "Cancel"
                    ) { dialog, _ -> dialog.dismiss() }
                    alertDialog.show()
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_properties).setAllOnClickListener {
                    val alertDialog = AlertDialog.Builder(folderVideoAct)
                    alertDialog.setTitle("Properties")
                    val one = "File: " + folderList[position].videoTitle
                    val path: String = folderList[position].videoPath

                    "video Path --- ${folderList[position].videoUri}".log()

                    val indexOfPath = path.lastIndexOf("/")
                    val two = "Path: " + path.substring(0, indexOfPath)
                    val three = "Size: " + Formatter.formatFileSize(
                        folderVideoAct, folderList[position].videoSize
                    )
                    val four = "Length: " + timeConversion(milliSeconds.toLong())
                    val format = folderList[position].videoMime
                    val five = "Format: $format"
                    alertDialog.setMessage(
                        """
                                            $one
                                            
                                            $two
                                            
                                            $three
                                            
                                            $four
                                            
                                            $five
                                            """.trimIndent()
                    )
                    alertDialog.setPositiveButton(
                        "OK"
                    ) { dialog, which -> dialog.dismiss() }
                    alertDialog.show()
                    bottomSheetDialog.dismiss()
                }
                bottomSheetDialog.setContentView(bsView)
                bottomSheetDialog.show()
            }
            root.setOnClickListener {
                onClick.invoke(folderList[position].videoTitle, folderList[position].videoUri, position)
            }
        }
    }

    private fun deleteVideo(videoFilePath: String, position: Int) {
        val contentResolver: ContentResolver = folderVideoAct.contentResolver

        val queryUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.Video.Media.DATA} = ?"
        val selectionArgs = arrayOf(videoFilePath)

        val deletedRows = contentResolver.delete(queryUri, selection, selectionArgs)

        if (deletedRows > 0) {
            ("Media file deleted successfully").log()
            folderList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, folderList.size)
        } else {
            ("Delete failed or file not found").log()
        }
    }

    fun timeConversion(millie: Long?): String? {
        return if (millie != null) {
            val seconds = millie / 1000
            val sec = seconds % 60
            val min = seconds / 60 % 60
            val hrs = seconds / (60 * 60) % 24
            if (hrs > 0) {
                String.format("%02d:%02d:%02d", hrs, min, sec)
            } else {
                String.format("%02d:%02d", min, sec)
            }
        } else {
            null
        }
    }
}