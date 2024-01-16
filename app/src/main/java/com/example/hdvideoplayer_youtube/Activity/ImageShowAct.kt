package com.example.hdvideoplayer_youtube.Activity

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.FolderImagesAct.Companion.currentImageList
import com.example.hdvideoplayer_youtube.Activity.WhatsAppActivity.Companion.myStatusList
import com.example.hdvideoplayer_youtube.Adapter.ImageViewPagerAdapter
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.ActivityImageShowBinding
import com.google.ads.sdk.AdsManager
import kotlin.math.abs

class ImageShowAct : BaseAct<ActivityImageShowBinding>() {

    lateinit var videoFrom: String
    lateinit var videoTitle: String
    var position: Int = 0

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityImageShowBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        getIntentData()

        binding.apply {
            shapeableImageView2.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@ImageShowAct) { finish() }
            }
            if (videoFrom == "Adapter") {
                materialTextView2.text = myStatusList[position].name
            } else {
                materialTextView2.text = currentImageList[position].imageTitle
            }
        }
    }

    private fun getIntentData() {
        position = intent.getIntExtra("position", 1)
        videoTitle = intent.getStringExtra("video_title").toString()

        videoFrom = if (intent.getStringExtra("video_from") != null) {
            intent.getStringExtra("video_from").toString()
        } else {
            "null"
        }

        if (videoFrom == "Adapter") {
            binding.OnlyImage.visibility = View.VISIBLE
            binding.vp.visibility = View.GONE
            binding.OnlyImage.load(myStatusList[position].uri)
        } else {
            binding.OnlyImage.visibility = View.GONE
            binding.vp.visibility = View.VISIBLE
            setSlider()
        }
    }

    fun setSlider() {
        try {
            val adapter = ImageViewPagerAdapter(this, currentImageList)
            binding.vp.clipToPadding = false
            binding.vp.clipChildren = false
            binding.vp.offscreenPageLimit = 3
            binding.vp.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            binding.vp.adapter = adapter
            binding.vp.currentItem = position
            "position ${position}".log()
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(4))
            transformer.addTransformer { page: View, position: Float ->
                page.scaleY = 0.2f * (1.0f - abs(position)) + 0.8f
            }
            binding.vp.setPageTransformer(transformer)
        } catch (e: Error) {
            "error  ::  ${e.message}".log()
        }
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@ImageShowAct) { finish() }
    }
}