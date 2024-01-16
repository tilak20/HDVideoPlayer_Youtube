package com.example.hdvideoplayer_youtube.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import com.downloadersocial.fastvideodownloader.Adapter.ShowDownloadAdapter
import com.example.hashtagapi.Utils.YTAPI
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Dialog.DeleteDialog
import com.example.hdvideoplayer_youtube.Main.BrowseActivity
import com.example.hdvideoplayer_youtube.ModelData.UrlModel
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.ActivityStorageBinding
import com.google.ads.sdk.AdsManager
import java.io.File
import java.util.Date
import java.util.concurrent.TimeUnit

class StorageActivity : BaseAct<ActivityStorageBinding>() {

    private lateinit var adapter: ShowDownloadAdapter
    val list = ArrayList<UrlModel>()
    private lateinit var lastModifiedDate: Date
    private var pos: Int = 0

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityStorageBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)
        initClick()

        binding.apply {

            imgback.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@StorageActivity) { finish() }
            }

            groupBrowser.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@StorageActivity) {
                    startActivity(
                        Intent(this@StorageActivity, BrowseActivity::class.java).putExtra(
                            "from", ""
                        ).putExtra("key", "")
                    )
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
            groupProgress.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@StorageActivity) {
                    startActivity(Intent(this@StorageActivity, ProgressActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
            groupSite.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@StorageActivity) {
                    startActivity(Intent(this@StorageActivity, SiteActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initClick() {
        list.clear()
        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + "/$YTAPI").path).listFiles()
            ?.forEach { it ->

                if (it.name.startsWith("YTD_")) {

                    if (it.path.isNotEmpty() && it.path.isNotBlank()) {

                        val retriever = MediaMetadataRetriever()
                        try {
                            retriever.setDataSource(it.path)

                            // Get the video duration in milliseconds
                            val durationString =
                                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                            val duration = durationString?.toLongOrNull() ?: 0
                            val formattedDuration = formatDuration(duration)

                            formattedDuration.log()
                            // Get the video width and height (resolution)
                            val widthString =
                                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
                            val heightString =
                                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
                            val width = widthString?.toIntOrNull() ?: 0
                            val height = heightString?.toIntOrNull() ?: 0

                            // Get file Size
                            val uri = Uri.parse(it.path)
                            val file = File(uri.path!!)
                            val size = file.length()

                            val formattedSize = formatFileSize(size)

                            val lastModifiedTime = getFileLastModifiedTime(it.path)

                            if (lastModifiedTime != -1L) {
                                // Successfully retrieved the last modified time.
                                // You can convert it to a human-readable format if needed.
                                lastModifiedDate = Date(lastModifiedTime)
                                ("Last Modified Time: $lastModifiedDate").log()
                            } else {
                                // Handle the case where the file does not exist or the path is invalid.
                                ("File does not exist or path is invalid.").log()
                            }
                            list.add(
                                UrlModel(
                                    it,
                                    formattedDuration,
                                    width,
                                    height,
                                    formattedSize,
                                    lastModifiedDate
                                )
                            )
                            // Close the retriever
                            adapter.notifyDataSetChanged()
                            retriever.release()
                        } catch (e: Exception) {
                            e.printStackTrace()
                            "e: ${e.localizedMessage}".log()
                        }
                    } else {
                        "null".log()
                    }

                }

                list.sortByDescending {
                    it.lastModifiedDate
                }
                adapter = ShowDownloadAdapter(this, list) { position ->
                    DeleteDialog(this@StorageActivity) {
                        deletefile(list[position].url.path)
                        initClick()
                        adapter.notifyItemChanged(position)
                        adapter.notifyItemRemoved(position)
                    }
                }
                binding.downloadrv.adapter = adapter
                if (list.size == 0) {
                    binding.imgEmpty.visible()
                } else {
                    binding.imgEmpty.gon()
                }
                binding.downloadrv.adapter?.notifyDataSetChanged()
            }
    }

    fun getFileLastModifiedTime(filePath: String): Long {
        val file = File(filePath)
        return if (file.exists()) {
            file.lastModified()
        } else {
            // Handle the case where the file does not exist or the path is invalid.
            -1L // You can choose any appropriate value to indicate an error or non-existence.
        }
    }

    fun formatFileSize(bytes: Long): String {
        val kiloBytes = bytes / 1000.0
        val megaBytes = kiloBytes / 1000.0
        val gigaBytes = megaBytes / 1000.0

        return when {
            gigaBytes >= 1.0 -> String.format("%.2f GB", gigaBytes)
            megaBytes >= 1.0 -> String.format("%.2f MB", megaBytes)
            kiloBytes >= 1.0 -> String.format("%.2f KB", kiloBytes)
            else -> String.format("%d bytes", bytes)
        }
    }

    fun formatDuration(durationMillis: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(durationMillis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis) % 60

        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    fun deletefile(filepath: String) {
        try {
            val file = File(filepath)
            if (file.exists()) {
                file.delete()
            }
            "file deleted Successfully".tos()
        } catch (e: Exception) {
            "${e.message}".log()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        "is Event".log()
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            try {
                list.removeAt(pos)
                binding.downloadrv.adapter?.notifyDataSetChanged()
                binding.downloadrv.adapter?.notifyItemRemoved(pos)
                binding.imgEmpty.visibility = if (list.size == 0) View.VISIBLE else View.GONE
            } catch (e: NullPointerException) {
                (e.message)?.log()
            }
        }
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@StorageActivity) { finish() }

    }
}