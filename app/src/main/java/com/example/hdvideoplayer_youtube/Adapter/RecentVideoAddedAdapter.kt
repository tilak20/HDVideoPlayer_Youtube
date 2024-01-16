package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.ContentUris
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.SystemClock
import android.provider.MediaStore
import android.text.TextUtils
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.convertUnixTimestampToDate
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.FoldervideoitemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File

class RecentVideoAddedAdapter(
    var activity: Activity,
    var folderList: ArrayList<Video>,
    var month: String,
    var onClick: (String, String, Int) -> Unit,
    var onClickDelete: (Uri,Int) -> Unit

) : RecyclerView.Adapter<RecentVideoAddedAdapter.ViewData>() {

    lateinit var bottomSheetDialog: BottomSheetDialog

    class ViewData(var binding: FoldervideoitemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(

        FoldervideoitemBinding.inflate(LayoutInflater.from(activity))
    )

    override fun getItemCount() = folderList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {
            shapeableImageView.load(folderList[position].videoUri)
            videoFileName.text = folderList[position].videoTitle
            val milliSeconds: Double = folderList[position].videoDuration.toDouble()


            txtModiFied.text = convertUnixTimestampToDate(folderList[position].videoDateadded.toLong())

            imgMore.setOnClickListener {
                bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetdialogTheme)
                val bsView = LayoutInflater.from(activity).inflate(
                    R.layout.video_bs_layout, it.findViewById(R.id.bottom_sheet)
                )

                bsView.findViewById<Group>(R.id.bs_play).setAllOnClickListener {
                    holder.itemView.performClick()
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_rename).setAllOnClickListener(View.OnClickListener {
                    val alertDialog = AlertDialog.Builder(activity)
                    alertDialog.setTitle("Rename to")
                    val editText = EditText(activity)
                    val path: String = folderList[position].videoPath
                    val file = File(path)
                    var videoName = file.name
                    videoName = videoName.substring(0, videoName.lastIndexOf("."))
                    editText.setText(videoName)
                    alertDialog.setView(editText)
                    editText.requestFocus()
                    alertDialog.setPositiveButton(
                        "OK",
                        DialogInterface.OnClickListener { dialog, which ->
                            if (TextUtils.isEmpty(editText.text.toString())) {
                                Toast.makeText(
                                    activity, "Can't rename empty file", Toast.LENGTH_SHORT
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
                                    activity.applicationContext.contentResolver
                                resolver.delete(
                                    MediaStore.Files.getContentUri("external"),
                                    MediaStore.MediaColumns.DATA + "=?",
                                    arrayOf(file.absolutePath)
                                )
                                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                                intent.data = Uri.fromFile(newFile)
                                activity.applicationContext.sendBroadcast(intent)
                                notifyDataSetChanged()
                                Toast.makeText(
                                    activity, "Video Renamed", Toast.LENGTH_SHORT
                                ).show()
                                SystemClock.sleep(200)
                                activity.recreate()
                            } else {
                                Toast.makeText(
                                    activity, "Process Failed", Toast.LENGTH_SHORT
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
                    activity.startActivity(
                        Intent.createChooser(
                            shareIntent, "Share Video via"
                        )
                    )
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_delete).setAllOnClickListener {
                    val alertDialog = AlertDialog.Builder(activity)
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


//                        val delete = File(folderList[position].videoPath).delete()
//                        if (delete) {
//                            activity.contentResolver.delete(contentUri, null, null)
//                            folderList.removeAt(position)
//                            notifyItemRemoved(position)
//                            notifyItemRangeChanged(position, folderList.size)
//                            Toast.makeText(activity, "Video Deleted", Toast.LENGTH_SHORT)
//                                .show()
//                        } else {
//                            Toast.makeText(activity, "can't deleted", Toast.LENGTH_SHORT)
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
                    val alertDialog = AlertDialog.Builder(activity)
                    alertDialog.setTitle("Properties")
                    val one = "File: " + folderList[position].videoTitle
                    val path: String = folderList[position].videoPath
                    val indexOfPath = path.lastIndexOf("/")
                    val two = "Path: " + path.substring(0, indexOfPath)
                    val three = "Size: " + Formatter.formatFileSize(
                        activity, folderList[position].videoSize
                    )
                    val four = "Length: " + timeConversion(milliSeconds.toLong())
                    val namewithFormat: String = folderList[position].videoTitle
                    val index = namewithFormat.lastIndexOf(".")
                    val format = folderList[position].videoMime
                    val five = "Format: $format"

//                    val metadataRetriever = MediaMetadataRetriever()
//                    metadataRetriever.setDataSource(folderList[position].videoUri)
//
//                    val height =
//                        metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT).toIntOrNull() ?: return null
//                    val width =
//                        metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
//                    val six = "Resolution: " + width + "x" + height
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
                onClick(folderList[position].videoTitle, folderList[position].videoUri, position)
            }

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