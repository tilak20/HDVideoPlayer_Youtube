package com.example.hdvideoplayer_youtube.Activity

import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.animation.OvershootInterpolator
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Activity.MainActivity.Companion.folderImageList
import com.example.hdvideoplayer_youtube.Adapter.FolderAdapter
import com.example.hdvideoplayer_youtube.ModelData.FolderMD
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.databinding.ActivityPhotosBinding
import com.google.ads.sdk.AdsManager
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

class PhotosAct : BaseAct<ActivityPhotosBinding>() {

    lateinit var folderAdapter: FolderAdapter
    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityPhotosBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)
        folderImageList.clear()
        getImageFolder()

        binding.apply {

            layout.search.gon()
            layout.dotMenu.gon()

            groupHome.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@PhotosAct) {
                    startActivity(Intent(this@PhotosAct, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
            groupSite.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@PhotosAct) {
                    startActivity(Intent(this@PhotosAct, SiteActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
            groupVideos.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@PhotosAct) {
                    startActivity(Intent(this@PhotosAct, TrendingActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
        }

        "image list size == 4 ${folderImageList.size}".log()
        setRV(folderImageList)
    }


    fun getImageFolder(): ArrayList<FolderMD> {
        val tempFolder = ArrayList<String>()

        val projection =
            arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID)

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null
        )
        cursor?.use {
            val bucketNameColumn =
                it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)

            while (it.moveToNext()) {
                val imageBucket = it.getString(bucketNameColumn)
                val imageBucketId = it.getString(bucketIdColumn)

                if (!tempFolder.contains(imageBucket)) {
                    tempFolder.add(imageBucket)
                    folderImageList.add(FolderMD(imageBucketId, imageBucket))
                }
            }
            "image list size == 1 ${folderImageList.size}".log()
        }
        return folderImageList
    }


    fun setRV(folderList: ArrayList<FolderMD>) {
        binding.apply {

            folderAdapter = FolderAdapter(this@PhotosAct, folderList, onClick = { it, folder ->

                AdsManager.getInstance().showInterstitialAd(this@PhotosAct) {
                    startActivity(
                        Intent(this@PhotosAct, FolderImagesAct::class.java).putExtra("position", it)
                            .putExtra("folderName", folder).putExtra("from", "Images")
                    )
                }
            })
//            val scaleInAnimationAdapter = ScaleInAnimationAdapter(folderAdapter)
//            scaleInAnimationAdapter.setDuration(500)
//            scaleInAnimationAdapter.setInterpolator(OvershootInterpolator())
//            scaleInAnimationAdapter.setFirstOnly(false)
            rcView.adapter = folderAdapter
        }
    }
}