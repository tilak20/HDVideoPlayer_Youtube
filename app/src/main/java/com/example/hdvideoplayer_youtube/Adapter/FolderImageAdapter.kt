package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.ContentUris
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.convertUnixTimestampToDate
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Activity.PhotosAct
import com.example.hdvideoplayer_youtube.ModelData.CurrentImageMd
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.FoldervideoitemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File

class FolderImageAdapter(
    var activity: Activity,
    var folderList: ArrayList<CurrentImageMd>,
    var onClick: (String, String, Int) -> Unit,
    var onDeleteClick: (String, Int) -> Unit
) : RecyclerView.Adapter<FolderImageAdapter.ViewData>() {

    lateinit var bottomSheetDialog: BottomSheetDialog

    class ViewData(var binding: FoldervideoitemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        FoldervideoitemBinding.inflate(LayoutInflater.from(activity))
    )

    override fun getItemCount() = folderList.size

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {
            shapeableImageView.load(folderList[position].imagePath)

            videoFileName.text = folderList[position].imageTitle
            txtModiFied.text = convertUnixTimestampToDate(folderList[position].imageDateadded.toLong())

            "date added == ${folderList[position].imageDateadded}".log()


            val milliSeconds = folderList[position].imageSize.toDouble()


//            ("Name == ${folderList[position].imageTitle}").log()

            imgMore.setOnClickListener {
                bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetdialogTheme)
                val bsView = LayoutInflater.from(activity).inflate(
                    R.layout.video_bs_layout, it.findViewById(R.id.bottom_sheet)
                )

                bsView.findViewById<View>(R.id.bs_play).visibility = View.GONE
                bsView.findViewById<Group>(R.id.bs_rename).setAllOnClickListener(View.OnClickListener {
                    val alertDialog = AlertDialog.Builder(activity)
                    alertDialog.setTitle("Rename to")
                    val editText = EditText(activity)
                    val path: String = folderList[position].imagePath
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
                    val uri = Uri.parse(folderList[position].imagePath)
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
                        onDeleteClick.invoke(folderList[position].imageUri,position)
                    }
                    alertDialog.setNegativeButton(
                        "Cancel"
                    ) { dialog, _ -> dialog.dismiss() }
                    alertDialog.show()
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_properties).setAllOnClickListener {

                    "path image == ${folderList[position].imagePath}".log()
                    "uri image == ${folderList[position].imageUri}".log()

                    val alertDialog = AlertDialog.Builder(activity)
                    alertDialog.setTitle("Properties")
                    val one = "File: " + folderList[position].imageTitle
                    val path: String = folderList[position].imagePath
                    val indexOfPath = path.lastIndexOf("/")
                    val two = "Path: " + path.substring(0, indexOfPath)
                    val three = "Size: " + Formatter.formatFileSize(
                        activity, folderList[position].imageSize
                    )
                    val namewithFormat: String = folderList[position].imageTitle
                    val index = namewithFormat.lastIndexOf(".")
                    val format = folderList[position].imageMime
                    val five = "Format: $format"


                    val options = BitmapFactory.Options()
                    options.inJustDecodeBounds = true // Set this to true to only decode image dimensions

                    BitmapFactory.decodeFile(folderList[position].imagePath, options)
                    val imageWidth = options.outWidth
                    val imageHeight = options.outHeight
                    val six = "Resolution: " + imageWidth + "x" + imageHeight
                    alertDialog.setMessage(
                        """
                                            $one
                                            
                                            $two
                                            
                                            $three
                                            
                                            $five
                                            
                                            $six
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
                onClick(folderList[position].imageTitle, folderList[position].imageUri, position)
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