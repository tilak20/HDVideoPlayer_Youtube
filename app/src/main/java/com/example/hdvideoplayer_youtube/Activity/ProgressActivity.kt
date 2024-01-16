package com.example.hdvideoplayer_youtube.Activity

import android.content.Intent
import android.view.LayoutInflater
import com.example.hashtagapi.Utils.mulaList
import com.example.hashtagapi.Utils.mulaVideoList
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.listofdownloadmodel
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Adapter.DownloadingAdapter
import com.example.hdvideoplayer_youtube.Dialog.VideoLatestDialog
import com.example.hdvideoplayer_youtube.Main.BrowseActivity
import com.example.hdvideoplayer_youtube.ModelData.Downloading
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.ActivityProgressBinding
import com.google.ads.sdk.AdsManager

class ProgressActivity : BaseAct<ActivityProgressBinding>() {

    lateinit var adapter: DownloadingAdapter

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityProgressBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        binding.apply {
            groupBrowser.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@ProgressActivity) {
                    startActivity(
                        Intent(
                            this@ProgressActivity, BrowseActivity::class.java
                        ).putExtra("from", "").putExtra("key", "")
                    )
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
            groupSite.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@ProgressActivity) {
                    startActivity(Intent(this@ProgressActivity, SiteActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
            groupStorage.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@ProgressActivity) {
                    startActivity(Intent(this@ProgressActivity, StorageActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }
            }
        }
        initClick()
    }

    private fun initClick() {
        binding.apply {

            imgback.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@ProgressActivity) { finish() }
            }

            mulaVideoList.observeForever {
                if (it.size != 0) {
                    if (!isFinishing) {
                        VideoLatestDialog(this@ProgressActivity, it, "Downloading")
                    }
                }
            }

            adapter = DownloadingAdapter(this@ProgressActivity, listofdownloadmodel)

            mulaList.observeForever { it ->

                if (it.none { it.progress.toInt() != 100 }) {
                    imgEmpty.visible()
                    downloadingRv.gon()
                } else {
                    imgEmpty.gon()
                    downloadingRv.visible()
                }

                adapter.update(it.filter {
                    it.progress.toInt() != 100
                } as ArrayList<Downloading>)
            }

            downloadingRv.adapter = adapter

            downloadingRv.adapter?.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@ProgressActivity) { finish() }
    }
}