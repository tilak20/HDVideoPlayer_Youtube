package com.example.hdvideoplayer_youtube.Adapter

import android.app.AlertDialog
import android.content.ContentResolver
import android.content.DialogInterface
import android.content.Intent
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
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hdvideoplayer_youtube.Activity.Application
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Activity.TempVideoAct
import com.example.hdvideoplayer_youtube.Activity.VideoPlayActivity
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.FoldervideoitemBinding
import com.google.ads.sdk.AdsManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File


class TempVideoAdapter(
    var tempVideoAct: TempVideoAct,
    var recentList: ArrayList<Video>,
    var s: String,
    var onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<TempVideoAdapter.ViewData>() {
    lateinit var bottomSheetDialog: BottomSheetDialog

    class ViewData(var binding: FoldervideoitemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        FoldervideoitemBinding.inflate(LayoutInflater.from(tempVideoAct))

    )

    override fun getItemCount() = recentList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {

//            val videoCollection: Collection<Video> = Application.getRecentMap().values
//            recentList.clear()
//            recentList.addAll(ArrayList(videoCollection))

            val file = File(recentList[position].videoPath)
            if (!file.exists()) {
                val rTempMap: MutableMap<String?, Video> = Application.getRecentMap().toMutableMap()
                rTempMap.remove(recentList[position].videoUri)

                "video not == ${recentList[position].videoPath}".log()

                Application.setRecentMap(rTempMap)
            }
            shapeableImageView.load(recentList[position].videoPath)

            videoFileName.text = recentList[position].videoTitle

            txtModiFied.text =
                Application.convertUnixTimestampToDate(recentList[position].videoDateadded.toLong())
            txtDuration.text = Application.getVideoDuration(recentList[position].videoDuration)
            txtDuration.setTextColor(
                ContextCompat.getColor(
                    tempVideoAct, R.color.md_theme_light_onSurfaceVariant
                )
            )

            imgMore.setOnClickListener {

                val milliSeconds = recentList[position].videoSize.toDouble()

                bottomSheetDialog = BottomSheetDialog(tempVideoAct, R.style.BottomSheetdialogTheme)
                val bsView = LayoutInflater.from(tempVideoAct).inflate(
                    R.layout.video_bs_layout, it.findViewById(R.id.bottom_sheet)
                )

                bsView.findViewById<Group>(R.id.bs_play).setAllOnClickListener {
                    val intent = Intent(tempVideoAct, VideoPlayActivity::class.java)
                    intent.putExtra("position", position)
                    intent.putExtra("video_title", recentList[position].videoTitle)
                    intent.putExtra("video_from", "RecentVideo")
                    tempVideoAct.startActivity(intent)
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_rename)
                    .setAllOnClickListener(View.OnClickListener {
                        val alertDialog = AlertDialog.Builder(tempVideoAct)
                        alertDialog.setTitle("Rename to")
                        val editText = EditText(tempVideoAct)
                        val path: String = recentList[position].videoPath
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
                                        tempVideoAct, "Can't rename empty file", Toast.LENGTH_SHORT
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
                                        tempVideoAct.applicationContext.contentResolver
                                    resolver.delete(
                                        MediaStore.Files.getContentUri("external"),
                                        MediaStore.MediaColumns.DATA + "=?",
                                        arrayOf(file.absolutePath)
                                    )
                                    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                                    intent.data = Uri.fromFile(newFile)
                                    tempVideoAct.applicationContext.sendBroadcast(intent)
                                    notifyDataSetChanged()
                                    Toast.makeText(
                                        tempVideoAct, "Video Renamed", Toast.LENGTH_SHORT
                                    ).show()
                                    SystemClock.sleep(200)
                                    tempVideoAct.recreate()
                                } else {
                                    Toast.makeText(
                                        tempVideoAct, "Process Failed", Toast.LENGTH_SHORT
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
                    val uri = Uri.parse(recentList[position].videoPath)
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "video/*"
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                    tempVideoAct.startActivity(
                        Intent.createChooser(
                            shareIntent, "Share Video via"
                        )
                    )
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_delete).setAllOnClickListener {
                    val alertDialog = AlertDialog.Builder(tempVideoAct)
                    alertDialog.setTitle("Delete")
                    alertDialog.setMessage("Do you want to delete this video")
                    alertDialog.setPositiveButton(
                        "Delete"
                    ) { dialog, which ->

                        val rTempMap: MutableMap<String?, Video> =
                            Application.getRecentMap().toMutableMap()
                        rTempMap.remove(recentList[position].videoUri)
                        Application.setRecentMap(rTempMap)

                        val videoCollection: Collection<Video> = Application.getRecentMap().values
                        recentList = ArrayList(videoCollection)

                        if (recentList.size == 0) {
                            "list size is 0".log()
                            onDeleteClick.invoke(0)
                        } else {
                            "list size not 0".log()
                            onDeleteClick.invoke(1)
                        }

                        notifyItemRemoved(position)
                        notifyDataSetChanged()
                    }
                    alertDialog.setNegativeButton(
                        "Cancel"
                    ) { dialog, _ -> dialog.dismiss() }
                    alertDialog.show()
                    bottomSheetDialog.dismiss()
                }
                bsView.findViewById<Group>(R.id.bs_properties).setAllOnClickListener {
                    val alertDialog = AlertDialog.Builder(tempVideoAct)
                    alertDialog.setTitle("Properties")
                    val one = "File: " + recentList[position].videoTitle
                    val path: String = recentList[position].videoPath

                    "video Path --- ${recentList[position].videoUri}".log()

                    val indexOfPath = path.lastIndexOf("/")
                    val two = "Path: " + path.substring(0, indexOfPath)
                    val three = "Size: " + Formatter.formatFileSize(
                        tempVideoAct, recentList[position].videoSize
                    )
                    val four = "Length: " + timeConversion(milliSeconds.toLong())
                    val format = recentList[position].videoMime
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

                AdsManager.getInstance().showInterstitialAd(tempVideoAct) {
                    val intent = Intent(tempVideoAct, VideoPlayActivity::class.java)
                    intent.putExtra("position", position)
                    intent.putExtra("video_title", recentList[position].videoTitle)
                    intent.putExtra("video_from", "RecentVideo")
                    tempVideoAct.startActivity(intent)
                }


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