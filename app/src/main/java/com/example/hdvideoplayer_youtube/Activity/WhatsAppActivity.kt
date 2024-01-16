package com.example.hdvideoplayer_youtube.Activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Adapter.StatusAdapter
import com.example.hdvideoplayer_youtube.ModelData.MyStatus
import com.example.hdvideoplayer_youtube.ModelData.WSMData
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityWhatsAppBinding
import com.example.hdvideoplayer_youtube.databinding.CustomTabWaBinding
import com.google.ads.sdk.AdsManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class WhatsAppActivity : BaseAct<ActivityWhatsAppBinding>() {

    companion object {
        lateinit var statusList: ArrayList<WSMData>
        lateinit var myStatusList: ArrayList<MyStatus>
    }

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityWhatsAppBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        val json = intent.getStringExtra("list")
        val type = object : TypeToken<ArrayList<WSMData>?>() {}.type
        statusList = Gson().fromJson(json, type)

        setTabViewPager()
        getVideo()

        binding.imgback.setOnClickListener {
            AdsManager.getInstance().showOnbackPressAdExtra(this@WhatsAppActivity) { finish() }

        }

    }

    fun getVideo() {
        myStatusList = ArrayList()
        myStatusList.clear()
        try {
            for (file in statusList) {
                if (file.name.contains(".mp4")) {
                    myStatusList.add(
                        MyStatus(file.path, file.name, Uri.parse(file.file))
                    )
                }
            }

        } catch (e: Exception) {
            ("error 2 = " + e.message).log()
        }
        val adapter = StatusAdapter(this, myStatusList) { pos, view, key, vid ->

            if (key == "Download") {
                onMenuClick(vid, pos, view, "video")
            } else {
                AdsManager.getInstance().showInterstitialAd(this@WhatsAppActivity) {
                    onItemClick(myStatusList, pos, "video")
                }
            }
        }
        binding.rvVideo.adapter = adapter
    }

    fun getImages() {
        myStatusList.clear()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            File("/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/.Statuses").listFiles()
        } else {
            File("/storage/emulated/0/WhatsApp/Media/.Statuses").listFiles()
        }

        for (file in statusList) {
            if (file.name.contains(".jpg")) {
                myStatusList.add(
                    MyStatus(file.path, file.name, Uri.parse(file.file))
                )
            }
        }

        val adapter = StatusAdapter(this, myStatusList) { pos, view, key, vid ->

            if (key == "Download") {
                onMenuClick(vid, pos, view, "image")
            } else {
                AdsManager.getInstance().showInterstitialAd(this@WhatsAppActivity) {
                    onItemClick(myStatusList, pos, "image")
                }
            }
        }
        binding.rvVideo.adapter = adapter
    }

    fun onItemClick(vid: ArrayList<MyStatus>, pos: Int, s: String) {

        if (s == "image") {
            startActivity(
                Intent(this, ImageShowAct::class.java).putExtra("status", vid[pos].uri.toString())
                    .putExtra("video_from", "Adapter").putExtra("position", pos)
            )
        } else {
            startActivity(
                Intent(this, VideoPlayActivity::class.java).putExtra(
                    "status", vid[pos].uri.toString()
                ).putExtra("video_from", "Adapter").putExtra("position", pos)
                    .putExtra("VideoName", vid[pos].name)
            )
        }

    }

    fun onMenuClick(vid: MyStatus, pos: Int, v: View?, s: String) {
        val menu = PopupMenu(this, v)
        menu.menuInflater.inflate(R.menu.menu_save, menu.menu)
        menu.setOnMenuItemClickListener { item: MenuItem ->
            if (item.itemId == R.id.save) {
                SaveStatusVid(vid.uri, s)
            }
            true
        }
        menu.show()
    }

    fun setTabViewPager() {
        binding.apply {

            val tab1 = tabLayout.newTab()
            val tabBinding1 = CustomTabWaBinding.inflate(
                LayoutInflater.from(this@WhatsAppActivity), null, false
            )
            tabBinding1.tabText.text = "Video"
            tab1.customView = tabBinding1.root
            tabLayout.addTab(tab1)

            val tab2 = tabLayout.newTab()
            val tabBinding2 = CustomTabWaBinding.inflate(
                LayoutInflater.from(this@WhatsAppActivity), null, false
            )
            tabBinding2.tabText.text = "Photos"
            tab2.customView = tabBinding2.root
            tabLayout.addTab(tab2)


            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab!!.position) {
                        0 -> {
                            AdsManager.getInstance().showInterstitialAd(this@WhatsAppActivity) {
                                getVideo()
                            }
                        }

                        1 -> {
                            AdsManager.getInstance().showInterstitialAd(this@WhatsAppActivity) {
                                getImages()
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@WhatsAppActivity) { finish() }
    }

}