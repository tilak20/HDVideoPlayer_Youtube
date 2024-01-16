package com.example.hdvideoplayer_youtube.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.BuildConfig
import com.example.hdvideoplayer_youtube.Utils.toast
import com.example.hdvideoplayer_youtube.databinding.ActivitySplashScreenBinding
import com.google.ads.sdk.Ad_inti
import com.google.ads.sdk.getDataListner
import com.google.gson.JsonArray
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : Ad_inti() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        ADSinit(this, BuildConfig.VERSION_CODE, "insttest1", object : getDataListner {
            @Override
            override fun onSuccess() {
                startActivity(Intent(this@SplashScreen, DesclaimerAct::class.java))
                finish()
            }

            override fun onUpdate(p0: String?) {
//                UpdateDialog(this@SplashScreen)
            }

            override fun onReload(p0: String?) {
                "on Reload".log()
            }

            override fun onNotSafe() {
                "Internet is Not Safe".toast(this@SplashScreen)
            }

            override fun onGetExtradata(jsonArray: JsonArray?) {

            }
        })
    }
}