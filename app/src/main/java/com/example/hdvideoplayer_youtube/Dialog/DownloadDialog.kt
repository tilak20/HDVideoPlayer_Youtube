package com.example.hdvideoplayer_youtube.Dialog

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaMuxer
import android.media.MediaScannerConnection
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.downloader.Error
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
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.listofdownloadmodel
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Adapter.FormatAdapter
import com.example.hdvideoplayer_youtube.ModelData.DownloadModel
import com.example.hdvideoplayer_youtube.ModelData.Downloading
import com.example.hdvideoplayer_youtube.ModelData.VideoModel.AdaptiveFormatsItem
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.loadImg
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.DownloadDialogItemBinding
import com.google.ads.sdk.AdsManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.nio.ByteBuffer
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class DownloadDialog(
    var activity: Activity,
    list1: ArrayList<AdaptiveFormatsItem>,
    url: String,
    audioBuffer: Int,
    var videoThumbnail: String,
    var videoTitle: String,
    var videoTime: String,
    var audioLength: Long,
    var cleanedTitle: String,
    var onClick: () -> Unit
) {

    private lateinit var durationMs: String
    var downloadId: Int = 0
    lateinit var filePath: String
    val dowid: Int = 0
    lateinit var formattedDate: String
    var isVideoDownload: Boolean = false
    var isAudioDownload: Boolean = false
    var videoName: String = ""
    var audioName: String = ""
    var quality: String = ""
    var videoBuffer: Int = 0
    var progressByte: Long = 0
    lateinit var currentSize: String
    var buffer: Int = 0
    var progressPer: Long = 0
    private val WRITE_PERMISSIONS = 2
    lateinit var status: Status

    companion object {
        lateinit var videoUrl: String
    }

    var dialog: BottomSheetDialog = BottomSheetDialog(activity)
    var binding: DownloadDialogItemBinding =
        DownloadDialogItemBinding.inflate(LayoutInflater.from(activity))

    init {
        dialog.setContentView(binding.root)

        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)


        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )

        binding.apply {
            val adapter =
                FormatAdapter(activity, list1, audioLength) { videoUrl, audioUrl, position, qual ->
                    btnDownload1.visible()
                    if (videoUrl.equals(null) && audioUrl.equals(null)) {
                        "url is null".log()
                    } else {
                        "videourl : ${list1[position].url}".log()
                        "videourl : $videoUrl".log()
                        videoBuffer = list1[position].bitrate!!

                        quality = qual

                        "audiourl : $url".log()

                        buffer = videoBuffer + audioBuffer
                        "Buffer: $videoBuffer || $audioBuffer || $buffer".log()

                        btnDownload1.setOnClickListener {
                            if (arePermissionsGranted()) {

                                PRDownloader.initialize(activity)
                                filePath =
                                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/$YTAPI/").path
                                videoName = "Y_${System.currentTimeMillis()}.webm"
                                downloadId =
                                    PRDownloader.download(videoUrl, filePath, videoName).build()
                                        .setOnStartOrResumeListener { "on start or resume".log() }
                                        .setOnPauseListener { "on Pause".log() }
                                        .setOnCancelListener { "on cancel".log() }
                                        .setOnProgressListener { progress ->
                                            progressByte = progress.currentBytes
                                            progressPer =
                                                progress.currentBytes * 100 / progress.totalBytes
                                            CoroutineScope(Dispatchers.Default).launch {

                                                currentSize = getFileSize(progress.currentBytes)

                                                try {
                                                    if (listofdownloadmodel.size != 0 && progressPer != 99L && progressPer != 100L) {
                                                        listofdownloadmodel.find { it.name == videoName }!!.progress =
                                                            progressPer
                                                        listofdownloadmodel.find { it.name == videoName }!!.currentSize =
                                                            currentSize
                                                        ("Download Progress: $progressPer ").log()
                                                        mulaList.postValue(listofdownloadmodel)
                                                    } else {
                                                        "Progress: $progressPer".log()
                                                    }

                                                } catch (e: Exception) {
                                                    "Exceptio n == ${e.message}".log()
                                                }

                                                delay(2000)
                                            }
                                        }.start(object : OnDownloadListener {
                                            override fun onDownloadComplete() {
                                                status = PRDownloader.getStatus(dowid)

                                                try {
                                                    listofdownloadmodel.find { it.name == videoName }!!.status =
                                                        status
                                                } catch (e: Exception) {
                                                    "Exceptio n == ${e.message}".log()
                                                }

                                                isVideoDownload = true
                                                if (isVideoDownload && isAudioDownload) {
                                                    "$isVideoDownload || $isAudioDownload".log()
                                                    isVideoDownload = false
                                                    isAudioDownload = false
                                                    mux(
                                                        Environment.getExternalStoragePublicDirectory(
                                                            Environment.DIRECTORY_DOWNLOADS
                                                        ).path + "/$YTAPI/$audioName",
                                                        Environment.getExternalStoragePublicDirectory(
                                                            Environment.DIRECTORY_DOWNLOADS
                                                        ).path + "/$YTAPI/$videoName",
                                                        Environment.getExternalStoragePublicDirectory(
                                                            Environment.DIRECTORY_DOWNLOADS
                                                        ).path + "/$YTAPI/YT_${System.currentTimeMillis()}.mp4",
                                                    )
                                                }

                                                "on complete".log()
                                            }

                                            override fun onError(error: Error?) {
                                                "error : $error".log()
                                            }
                                        })

                                status = PRDownloader.getStatus(dowid)
//                                currentSize = getFileSize(progressByte)
//                                currentSize = getFileSize(fileSize)
                                currentSize = getFileSize(progressByte)
                                listofdownloadmodel.add(
                                    Downloading(
                                        filePath,
                                        progressPer,
                                        downloadId,
                                        videoName,
                                        videoThumbnail,
                                        quality,
                                        status,
                                        currentSize
                                    )
                                )

                                filePath =
                                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/$YTAPI/").path
                                audioName = "Y_${System.currentTimeMillis()}.webm"
                                downloadId = PRDownloader.download(url, filePath, audioName).build()
                                    .setOnStartOrResumeListener { "on start or resume".log() }
                                    .setOnPauseListener { "on Pause".log() }
                                    .setOnCancelListener { "on cancel".log() }
                                    .setOnProgressListener { progress ->
                                        progressByte = progress.currentBytes
                                        progressPer =
                                            progress.currentBytes * 100 / progress.totalBytes

                                    }.start(object : OnDownloadListener {
                                        override fun onDownloadComplete() {

                                            isAudioDownload = true
                                            if (isVideoDownload && isAudioDownload) {
                                                "$isVideoDownload || $isAudioDownload".log()
                                                isVideoDownload = false
                                                isAudioDownload = false
                                                mux(
                                                    Environment.getExternalStoragePublicDirectory(
                                                        Environment.DIRECTORY_DOWNLOADS
                                                    ).path + "/$YTAPI/$audioName",
                                                    Environment.getExternalStoragePublicDirectory(
                                                        Environment.DIRECTORY_DOWNLOADS
                                                    ).path + "/$YTAPI/$videoName",
                                                    Environment.getExternalStoragePublicDirectory(
                                                        Environment.DIRECTORY_DOWNLOADS
                                                    ).path + "/$YTAPI/YT_${System.currentTimeMillis()}.mp4",
                                                )
                                            }

                                            "on complete".log()
                                        }

                                        override fun onError(error: Error?) {
                                            "error : $error".log()
                                        }
                                    })

                                status = PRDownloader.getStatus(dowid)
                                currentSize = getFileSize(progressByte)

                                dialog.dismiss()

                            } else {
                                requestPermission()
                            }
                        }
                    }
                }
            formatRv.adapter = adapter

            imgThumbnail.loadImg(videoThumbnail)
            txtTitle.text = videoTitle
            txtTime.text = videoTime
        }

        dialog.show()
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.WRITE_SETTINGS
                ), WRITE_PERMISSIONS
            )
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_SETTINGS
                ), WRITE_PERMISSIONS
            )
        }
    }

    private fun arePermissionsGranted(): Boolean {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            val permissions: Array<String> = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        activity, permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        } else {
            val permissions: Array<String> = arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO
            )
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        activity, permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    @SuppressLint("WrongConstant")
    fun mux(audioFile: String, videoFile: String, outFile: String) {
        val someTask = @SuppressLint("StaticFieldLeak") object : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                try {
                    val videoExtractor = MediaExtractor()
                    videoExtractor.setDataSource(videoFile)
                    videoExtractor.selectTrack(0)
                    val videoFormat = videoExtractor.getTrackFormat(0)

                    val audioExtractor = MediaExtractor()
                    audioExtractor.setDataSource(audioFile)
                    audioExtractor.selectTrack(0)
                    val audioFormat = audioExtractor.getTrackFormat(0)

                    // Init muxer
                    val muxer = MediaMuxer(outFile, MediaMuxer.OutputFormat.MUXER_OUTPUT_WEBM)
                    val videoIndex = muxer.addTrack(videoFormat)
                    val audioIndex = muxer.addTrack(audioFormat)
                    muxer.start()

                    // Prepare buffer for copying
//                    val maxChunkSize = 1024 * 1024 * 100
                    "buffer merge: $buffer".log()
                    val buffer = ByteBuffer.allocate(buffer)
                    val bufferInfo = MediaCodec.BufferInfo()

                    // Copy Video
                    while (true) {
                        val chunkSize = videoExtractor.readSampleData(buffer, 0)

                        if (chunkSize > 0) {
                            bufferInfo.presentationTimeUs = videoExtractor.sampleTime
                            bufferInfo.flags = videoExtractor.sampleFlags
                            bufferInfo.size = chunkSize

                            muxer.writeSampleData(videoIndex, buffer, bufferInfo)

                            videoExtractor.advance()

                        } else {
                            break
                        }
                    }
                    listofdownloadmodel.find { it.name == videoName }!!.progress = 99L
                    listofdownloadmodel.find { it.name == videoName }!!.currentSize = currentSize
                    ("Download Progress: $progressPer ").log()
                    mulaList.postValue(listofdownloadmodel)

                    // Copy audio
                    while (true) {
                        val chunkSize = audioExtractor.readSampleData(buffer, 0)

                        if (chunkSize >= 0) {
                            bufferInfo.presentationTimeUs = audioExtractor.sampleTime
                            bufferInfo.flags = audioExtractor.sampleFlags
                            bufferInfo.size = chunkSize

                            muxer.writeSampleData(audioIndex, buffer, bufferInfo)
                            audioExtractor.advance()
                        } else {
                            break
                        }
                    }

                    // Cleanup
                    muxer.stop()
                    muxer.release()

                    videoExtractor.release()
                    audioExtractor.release()


                    return true
                } catch (e: Exception) {
                    e.printStackTrace()
                    return false
                }
            }

            override fun onProgressUpdate(vararg values: Void?) {
                values.log()
            }

            override fun onPostExecute(result: Boolean) {
                Log.d("FATZ", "onPostExecute: $result")
                Log.d("FATZ", "onPostExecute: $videoFile")
                Log.d("FATZ", "onPostExecute: $audioFile")

                listofdownloadmodel.find { it.name == videoName }!!.progress = 100L
                listofdownloadmodel.find { it.name == videoName }!!.currentSize = currentSize
                ("Download Progress: $progressPer ").log()
                mulaList.postValue(listofdownloadmodel)

                val newPath =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/$YTAPI/YTD_${cleanedTitle}.mp4"
                if (File(outFile).exists()) {
                    if (File(outFile).renameTo(File(newPath))) {
                        "file renamed".log()
                    } else {
                        "file rename erroe".log()
                    }
                } else {
                    "file not exist".log()
                }

                if (result) {

                    val downloadDirectory = File(
                        Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS + "/$YTAPI"
                        ).path
                    ).listFiles()

                    val mostRecentFile = downloadDirectory?.maxByOrNull { it.lastModified() }

                    listofVideo.add(mostRecentFile!!)
                    mulaVideoList.postValue(listofVideo)

                    ("last modified == ${mostRecentFile.name}").log()
//                                        ("last modified == ${mostRecentFile.toURI()}").log()

                    File(
                        Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS + "/$YTAPI"
                        ).path
                    ).listFiles()?.forEach {
                        if (it.exists()) {
                            val lastModifiedTimestamp = it.lastModified()
                            val sdf = SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
                            )
                            formattedDate = sdf.format(Date(lastModifiedTimestamp))
//                                                ("File was modified : $formattedDate").log()
                        } else {
                            ("File not found").log()
                        }

                        list.add(DownloadModel(it, formattedDate))
                        scanMediaFile(it.path)
                    }
                    File(
                        Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS + "/$YTAPI"
                        ).path
                    ).listFiles()?.forEach {
                        if (it.name.endsWith(".mp4")) {
                            if (it.exists()) {
                                val lastModifiedTimestamp = it.lastModified()
                                val sdf = SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
                                )
                                formattedDate = sdf.format(
                                    Date(
                                        lastModifiedTimestamp
                                    )
                                )
//                                                    ("File was last modified on: $formattedDate").log()
                            } else {
                                ("File not found").log()
                            }

                            list.add(DownloadModel(it, formattedDate))
                            latestVideo.postValue(list)
                            scanMediaFile(it.path)
                        }
                    }


                    Log.e("FATZ", "onPostExecute: Success")
//                    binding.txtProgress.text = "Done"
                    scanMediaFile(outFile)
                    // video and audio deleted after merging
                    val fileToDelete: File = File(videoFile)
                    if (fileToDelete.exists()) {
                        if (fileToDelete.delete()) {
                            Log.e("FATZ", "File deleted successfully.")
                        } else {
                            Log.e("FATZ", "Failed to delete the file.")
                        }
                    } else {
                        Log.e("FATZ", "File does not exist V.")
                    }
                    val audioFileToDelete: File = File(audioFile)
                    if (audioFileToDelete.exists()) {
                        if (audioFileToDelete.delete()) {
                            Log.e("FATZ", "File deleted successfully.")
                        } else {
                            Log.e("FATZ", "Failed to delete the file.")
                        }
                    } else {
                        Log.e("FATZ", "File does not exist A.")
                    }
                    // Merging operation was successful
                    // Handle success, e.g., show a success message or play the merged video.
                } else {
                    Log.e("FATZ", "onPostExecute: Failed")
                }
            }
        }

        someTask.execute()
    }

    fun scanMediaFile(path: String?) {
        path?.let {
            val paths = arrayOf(path)
            MediaScannerConnection.scanFile(activity, paths, null, null)
        }
    }
}