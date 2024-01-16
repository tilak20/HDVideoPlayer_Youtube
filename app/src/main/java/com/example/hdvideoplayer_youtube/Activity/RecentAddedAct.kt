package com.example.hdvideoplayer_youtube.Activity

import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.currentVideoList
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.main
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.videoList
import com.example.hdvideoplayer_youtube.Activity.MainActivity.Companion.recentVideList
import com.example.hdvideoplayer_youtube.Adapter.RecentVideoAddedAdapter
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityRecentAddedBinding
import com.google.ads.sdk.AdsManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class RecentAddedAct : BaseAct<ActivityRecentAddedBinding>() {

    lateinit var fourMonthsAgo: Calendar
    lateinit var recentVideoAddedAdapter: RecentVideoAddedAdapter
    val dateFormat = SimpleDateFormat("MM")
    val date = Date()
    var deletepos: Int = 0
    lateinit var videoUri: Uri

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityRecentAddedBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        fourMonthsAgo = Calendar.getInstance().apply {
            add(Calendar.MONTH, -4)
        }

        currentVideoList.clear()
        getAllPlayableVideo()

        currentVideoList = getAllPlayableRecent()
        currentVideoList.addAll(videoList.filter {
            val videoDate =
                Calendar.getInstance().apply { timeInMillis = it.videoDateadded.toLong() * 1000L }
            videoDate >= fourMonthsAgo
        }.sortedByDescending { it.videoDateadded })
        setRV(currentVideoList)

        binding.shapeableImageView2.setOnClickListener {
            AdsManager.getInstance().showOnbackPressAdExtra(this@RecentAddedAct) { finish() }
        }
        recentVideList.forEach {
            "date added == ${main(it.videoDateadded)}".log()
        }
    }

    fun setRV(folderList: ArrayList<Video>) {
        binding.apply {
            recentVideoAddedAdapter = RecentVideoAddedAdapter(this@RecentAddedAct,
                folderList,
                dateFormat.format(date),
                onClick = { title, uri, position ->

                    val rTempMap: MutableMap<String?, Video> =
                        Application.getRecentMap().toMutableMap()
                    rTempMap[currentVideoList[position].videoUri] = currentVideoList[position]
                    Application.setRecentMap(rTempMap)


                    AdsManager.getInstance().showInterstitialAd(this@RecentAddedAct) {
                        startActivity(
                            Intent(
                                this@RecentAddedAct, VideoPlayActivity::class.java
                            ).putExtra("position", position).putExtra("video_title", title)
                                .putExtra("video_from", "Folder")
                        )
                    }
                },
                onClickDelete = { it, pos ->
                    videoUri = it
                    deletepos = pos

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                        contentResolver.delete(videoUri, null, null)
                        currentVideoList.removeAt(deletepos)

                        currentVideoList.addAll(videoList.filter {
                            val videoDate = Calendar.getInstance()
                                .apply { timeInMillis = it.videoDateadded.toLong() * 1000L }
                            videoDate >= fourMonthsAgo
                        }.sortedByDescending { it.videoDateadded })

                        setRV(currentVideoList)
                        recentVideoAddedAdapter.notifyItemRemoved(deletepos)
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
//            val scaleInAnimationAdapter = ScaleInAnimationAdapter(recentVideoAddedAdapter)
//            scaleInAnimationAdapter.setDuration(500)
//            scaleInAnimationAdapter.setInterpolator(OvershootInterpolator())
//            scaleInAnimationAdapter.setFirstOnly(false)
            rcView.adapter = recentVideoAddedAdapter
        }
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
                        currentVideoList.addAll(videoList.filter {
                            val videoDate = Calendar.getInstance()
                                .apply { timeInMillis = it.videoDateadded.toLong() * 1000L }
                            videoDate >= fourMonthsAgo
                        }.sortedByDescending { it.videoDateadded })
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
                        recentVideoAddedAdapter.notifyDataSetChanged()
                        recentVideoAddedAdapter.notifyItemRemoved(deletepos)
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

        currentVideoList = getAllPlayableRecent()

        currentVideoList.clear()
        currentVideoList.addAll(videoList.filter {
            val videoDate =
                Calendar.getInstance().apply { timeInMillis = it.videoDateadded.toLong() * 1000L }
            videoDate >= fourMonthsAgo
        }.sortedByDescending { it.videoDateadded })
        setRV(currentVideoList)
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@RecentAddedAct) { finish() }

    }
}