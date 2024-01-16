package com.example.hdvideoplayer_youtube.Dialog


import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaMetadataRetriever
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Status
import com.example.hashtagapi.Utils.YTAPI
import com.example.hashtagapi.Utils.getFileSize
import com.example.hashtagapi.Utils.latestVideo
import com.example.hashtagapi.Utils.list
import com.example.hashtagapi.Utils.listofVideo
import com.example.hashtagapi.Utils.mulaList
import com.example.hashtagapi.Utils.mulaVideoList
import com.example.hashtagapi.Utils.scanMediaFile
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.datalist
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.listofdownloadmodel
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.onBackground
import com.example.hdvideoplayer_youtube.Adapter.VideoQulityAdapter
import com.example.hdvideoplayer_youtube.ModelData.AhaModel
import com.example.hdvideoplayer_youtube.ModelData.DataList
import com.example.hdvideoplayer_youtube.ModelData.DownloadModel
import com.example.hdvideoplayer_youtube.ModelData.Downloading
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.DetectvideofileBinding
import com.google.ads.sdk.AdsManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.concurrent.TimeUnit

@SuppressLint("SetTextI18n", "NotifyDataSetChanged")
class DetectVideoDialog(var activity: Activity, res: AhaModel) {

    lateinit var formattedDate: String
    var progressByte: Long = 0
    lateinit var currentSize: String
    lateinit var status: Status
    lateinit var quality: String
    var dowid: Int = 0
    lateinit var progressUpdateTimer: Timer
    var progressPer: Long = 0
    var position: Int = 0
    var dialog: BottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetdialogTheme)
    var binding: DetectvideofileBinding =
        DetectvideofileBinding.inflate(LayoutInflater.from(activity))

    companion object {
        lateinit var videoUrl: String
    }

    init {
        dialog.setContentView(binding.root)
        PRDownloader.initialize(activity)
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        datalist.clear()

        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent)

        if (res.dataList!!.isNotEmpty()) {
            videoUrl = res.dataList!![0].mediaUrlList!![0]
        }

        binding.apply {

            tital.text = "${System.currentTimeMillis() / 1000}.mp4"

            res.dataList!!.filter { it.mediaUrlList?.size == 1 }.forEach {
                it.isSelected = false
                datalist.add(it)
            }
            "data list == 2 == ${datalist.size}".log()

            if (datalist.isNotEmpty()) {
                image.load(res.dataList!![0].thumbnailUrl.ifEmpty { videoUrl })
                videoUrl = datalist[0].mediaUrlList?.get(0) ?: ""
                datalist[0].isSelected = true
            }

            onBackground {
                try {
                    if (videoUrl.isNotEmpty()) {
                        val retriever = MediaMetadataRetriever()
                        videoUrl.log()
                        retriever.setDataSource(videoUrl)
                        val durationStr =
                            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                        val duration = durationStr!!.toLong()
                        ("D: $duration").log()

                        val hours = TimeUnit.MILLISECONDS.toHours(duration)
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60
                        val seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60
                        val durationtv = String.format("%02d:%02d:%02d", hours, minutes, seconds)

                        if (datalist.isNotEmpty() && datalist.any { it.filesize == 0L }) {
                            callLength(datalist, 0)
                        }

                        activity.runOnUiThread {
                            videoduration.text = durationtv
                            rvvideo.adapter?.notifyDataSetChanged()
                        }
                    }
                } catch (e: Exception) {
                }
            }

            if (datalist.size != 0 && datalist != null) {
                datalist.sortByDescending { it.filesize }
            }

            if (datalist.size != 0) {

                rvvideo.adapter = VideoQulityAdapter(activity, datalist, onClick = { it, pos ->

                    datalist.forEach { it.isSelected = false }
                    it.isSelected = true
                    position = pos
                    rvvideo.adapter!!.notifyDataSetChanged()
                    videoUrl = it.mediaUrlList?.get(0) ?: ""

                    if (it.quality != null) {
                        quality = it.quality
                    }
                    ("video : $videoUrl").log()
                }, quality = {

                    quality = if (!it.equals(null)) {
                        it
                    } else {
                        "-"
                    }
                    ("Quality == $it").log()
                })
            }

            downloadbtn.setOnClickListener {
                val filePath = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/$YTAPI/").path
                val fileName = "YTD_${System.currentTimeMillis()}.mp4"
                val downloadId = PRDownloader.download(videoUrl, filePath, fileName).build()
                    .setOnStartOrResumeListener { "on start or resume".log() }
                    .setOnPauseListener { "on Pause".log() }
                    .setOnCancelListener { "on cancel".log() }.setOnProgressListener { progress ->
                        progressByte = progress.currentBytes
                        progressPer = progress.currentBytes * 100 / progress.totalBytes
                        CoroutineScope(Dispatchers.Default).launch {

                            currentSize = getFileSize(progress.currentBytes)

                            ("Download Progress: $currentSize ").log()

                            if (listofdownloadmodel.size != 0) {
                                listofdownloadmodel.find { it.name == fileName }!!.progress =
                                    progressPer
                                listofdownloadmodel.find { it.name == fileName }!!.currentSize =
                                    currentSize

                                mulaList.postValue(listofdownloadmodel)
                            }

                            delay(2000)
                        }

                    }.start(object : OnDownloadListener {
                        override fun onDownloadComplete() {
                            status = PRDownloader.getStatus(dowid)

                            listofdownloadmodel.find { it.name == fileName }!!.status = status

                            val downloadDirectory =
                                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/${YTAPI}").path).listFiles()

                            val mostRecentFile =
                                downloadDirectory?.maxByOrNull { it.lastModified() }


                            listofVideo.add(mostRecentFile!!)
                            mulaVideoList.postValue(listofVideo)

                            ("last modified == ${mostRecentFile.name}").log()

                            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/${YTAPI}").path).listFiles()
                                ?.forEach {

                                    if (it.exists()) {
                                        val lastModifiedTimestamp = it.lastModified()
                                        val sdf = SimpleDateFormat(
                                            "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
                                        )
                                        formattedDate = sdf.format(Date(lastModifiedTimestamp))
                                        ("File was modified : $formattedDate").log()
                                    } else {
                                        ("File not found").log()
                                    }

                                    list.add(DownloadModel(it, formattedDate))
                                    scanMediaFile(activity, it.path)
                                }
                            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/${YTAPI}").path).listFiles()
                                ?.forEach {
                                    if (it.name.endsWith(".mp4")) {
                                        if (it.exists()) {
                                            val lastModifiedTimestamp = it.lastModified()
                                            val sdf = SimpleDateFormat(
                                                "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
                                            )
                                            formattedDate = sdf.format(Date(lastModifiedTimestamp))
                                            ("File was last modified on: $formattedDate").log()
                                        } else {
                                            ("File not found").log()
                                        }

                                        list.add(DownloadModel(it, formattedDate))
                                        latestVideo.postValue(list)
                                        scanMediaFile(activity, it.path)
                                    }
                                }
                            "on complete".log()
                        }

                        override fun onError(error: com.downloader.Error?) {
                            "error : $error".log()
                        }


                    })

                status = PRDownloader.getStatus(dowid)
                currentSize = getFileSize(progressByte)

                listofdownloadmodel.add(
                    Downloading(
                        filePath,
                        progressPer,
                        downloadId,
                        fileName,
                        videoUrl,
                        quality,
                        status,
                        currentSize
                    )
                )

                dialog.dismiss()

            }
            dialog.show()
        }
    }


    fun getDateAndTimeFromFile(filePath: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date(filePath))
    }

    fun callLength(datalist: ArrayList<DataList>, i: Int) {
        ("Size 1 == : $i").log()
        if (datalist.size != i && !activity.isDestroyed) {
            val url by lazy { URL(datalist[i].mediaUrlList!![0]) }
            val urlConnection by lazy { url.openConnection() as HttpURLConnection }
            urlConnection.requestMethod = "HEAD"
            urlConnection.connect()
            val contentLength: Int = urlConnection.contentLength
            datalist[i].filesize = contentLength.toLong()

            urlConnection.disconnect()
            callLength(datalist, i + 1)

            datalist.sortByDescending {
                it.filesize
            }
        }
    }

}