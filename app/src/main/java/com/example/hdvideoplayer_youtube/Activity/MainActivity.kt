package com.example.hdvideoplayer_youtube.Activity

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.core.app.ActivityCompat
import com.example.hashtagapi.Utils.READ_PERMISSIONS
import com.example.hashtagapi.Utils.WRITE_PERMISSIONS
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Adapter.FolderAdapter
import com.example.hdvideoplayer_youtube.Adapter.RecentPlayeAdapter
import com.example.hdvideoplayer_youtube.ModelData.FolderMD
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityMainBinding
import com.google.ads.sdk.AdsManager
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : BaseAct<ActivityMainBinding>() {

    lateinit var bs_share: Group
    lateinit var bs_play: Group
    lateinit var bs_rate: Group
    lateinit var folderAdapter: FolderAdapter
    lateinit var recentPlayeAdapter: RecentPlayeAdapter
    lateinit var bottomSheetDialog: BottomSheetDialog

    companion object {
        var folderList = arrayListOf<FolderMD>()
        var folderImageList = arrayListOf<FolderMD>()
        var recentVideList = arrayListOf<Video>()
        lateinit var recentList: ArrayList<Video>
    }

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        val videoCollection: Collection<Video> = Application.getRecentMap().values
        recentList = ArrayList(videoCollection)

        binding.apply {

            if (!recentList.equals(null) || recentList.size != 0) {
                setRecentAdapter(recentList)
                group.visibility = View.VISIBLE
            } else {
                group.visibility = View.GONE
                "recent video null".log()
            }

            layout.dotMenu.setOnClickListener {
                bottomSheetDialog =
                    BottomSheetDialog(this@MainActivity, R.style.BottomSheetdialogTheme)
                val bsView = LayoutInflater.from(this@MainActivity).inflate(
                    R.layout.menu_dialog, it.findViewById(R.id.bottom_sheet)
                )

                bottomSheetDialog.setContentView(bsView)
                bottomSheetDialog.show()
                bs_play = bsView.findViewById(R.id.bs_play)
                bs_rate = bsView.findViewById(R.id.bs_rate)
                bs_share = bsView.findViewById(R.id.bs_share)

                bs_share.setAllOnClickListener {
                    shareUs()
                    bottomSheetDialog.dismiss()
                }

                bs_rate.setAllOnClickListener {
                    rateUs()
                    bottomSheetDialog.dismiss()
                }

                bs_play.setAllOnClickListener {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        getAllPlayableVideo()
                        getFolder()
                        folderAdapter.notifyDataSetChanged()
                    } else {
                        if (checkPermission()) {
                            getAllPlayableVideo()
                            folderAdapter.notifyDataSetChanged()
                        } else {
                            requestPermission()
                        }
                    }
                    bottomSheetDialog.dismiss()
                }
            }

            layout.search.setOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(Intent(this@MainActivity, SearchVideoAct::class.java))
                }
            }

            imageFilterView3.setOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(Intent(this@MainActivity, TempVideoAct::class.java))
                }
            }

            groupVideos.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(Intent(this@MainActivity, TrendingActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

            groupSite.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(Intent(this@MainActivity, SiteActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
            groupPhotos.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(Intent(this@MainActivity, PhotosAct::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
            youtubeDownload.setOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(Intent(this@MainActivity, TrendingActivity::class.java))
                }
            }
        }

        binding.group2.setAllOnClickListener {
            AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                startActivity(Intent(this, RecentAddedAct::class.java))
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getAllPlayableVideo()
            getFolder()
        } else {
            if (checkPermission()) {
                getAllPlayableVideo()
            } else {
                requestPermission()
            }
        }
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_SETTINGS
                    ), READ_PERMISSIONS
                )
            } catch (e: Exception) {
                Log.d("FATZ", "Error $e")
            }
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_SETTINGS
                ), WRITE_PERMISSIONS
            )
        }
    }

    fun setRecentAdapter(recentList: ArrayList<Video>) {

        binding.apply {
            recentPlayeAdapter = RecentPlayeAdapter(this@MainActivity, recentList, "MainAct")
            recentPlayedRV.adapter = recentPlayeAdapter
        }
    }

    fun getFolder(): ArrayList<FolderMD> {
        folderList.clear()
        val tempFolder = ArrayList<String>()

        val projection =
            arrayOf(MediaStore.Video.Media.BUCKET_DISPLAY_NAME, MediaStore.Video.Media.BUCKET_ID)
        val cursor = this@MainActivity.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null
        )
        cursor?.use {
            val bucketNameColumn =
                it.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
            while (it.moveToNext()) {
                val videoBucket = it.getString(bucketNameColumn)
                val videoBucketId = it.getString(bucketIdColumn)

                if (!tempFolder.contains(videoBucket)) {
                    tempFolder.add(videoBucket)
                    if (videoBucket == null || videoBucketId == null) {
                        folderList.add(FolderMD(videoBucketId, videoBucketId))
                    } else {
                        folderList.add(FolderMD(videoBucketId, videoBucket))
                    }
                }
            }
            setRV(folderList)
        }
        return folderList
    }

    fun setRV(folderList: ArrayList<FolderMD>) {
        binding.apply {
            folderAdapter = FolderAdapter(this@MainActivity, folderList) { it, folder ->

                AdsManager.getInstance().showInterstitialAd(this@MainActivity) {
                    startActivity(
                        Intent(
                            this@MainActivity, FolderVideoAct::class.java
                        ).putExtra("position", it).putExtra("folderName", folder)
                            .putExtra("from", "Videos")
                    )
                }


            }
            rcView.adapter = folderAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        getFolder()

        val videoCollection: Collection<Video> = Application.getRecentMap().values
        recentList = ArrayList(videoCollection)

        if (recentList.size != 0) {
            setRecentAdapter(recentList)
            binding.group.visibility = View.VISIBLE
            "recent ${recentList.size}".log()
        } else {
            binding.group.visibility = View.GONE
            "recent null == ${recentList.size}".log()
        }
    }
}