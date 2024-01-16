package com.example.hdvideoplayer_youtube.Activity

import android.view.LayoutInflater
import android.view.View
import com.example.hdvideoplayer_youtube.Activity.MainActivity.Companion.recentList
import com.example.hdvideoplayer_youtube.Adapter.TempVideoAdapter
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityTempVideoBinding
import com.google.ads.sdk.AdsManager

class TempVideoAct : BaseAct<ActivityTempVideoBinding>() {
    lateinit var tempVideoAdapter: TempVideoAdapter

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityTempVideoBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        setRecentAdapter(recentList)

        binding.shapeableImageView2.setOnClickListener {
            AdsManager.getInstance().showOnbackPressAdExtra(this@TempVideoAct) { finish() }
        }
    }

    fun setRecentAdapter(recentList: ArrayList<Video>) {
        binding.apply {
            tempVideoAdapter = TempVideoAdapter(this@TempVideoAct, recentList, "TempAct") {

                if (it == 0) {
                    rcView.visibility = View.GONE
                    txtNotFound.visibility = View.VISIBLE
                } else {
                    rcView.visibility = View.VISIBLE
                    txtNotFound.visibility = View.GONE
                }
            }
            rcView.adapter = tempVideoAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        val videoCollection: Collection<Video> = Application.getRecentMap().values
        recentList = java.util.ArrayList(videoCollection)

        setRecentAdapter(recentList)
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@TempVideoAct) { finish() }

    }
}