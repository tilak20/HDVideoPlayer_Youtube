package com.example.hdvideoplayer_youtube.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.downloadersocial.fastvideodownloader.ApiClient.YoutubeClient.Companion.getRetrofit
import com.downloadersocial.fastvideodownloader.ApiInterface.TrendingInterface
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Adapter.TrendingAdapter
import com.example.hdvideoplayer_youtube.ModelData.Client
import com.example.hdvideoplayer_youtube.ModelData.Context
import com.example.hdvideoplayer_youtube.ModelData.ItemsItem
import com.example.hdvideoplayer_youtube.ModelData.Request
import com.example.hdvideoplayer_youtube.ModelData.TrendingListBodyModel
import com.example.hdvideoplayer_youtube.ModelData.TrendingListModel
import com.example.hdvideoplayer_youtube.ModelData.User
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.ActivityTrendingBinding
import com.google.ads.sdk.AdsManager
import com.onesignal.OneSignal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingActivity : BaseAct<ActivityTrendingBinding>() {
    var list: ArrayList<ItemsItem> = arrayListOf()
    private val WRITE_PERMISSIONS = 2

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityTrendingBinding.inflate(layoutInflater)

    override fun initUI() {

        OneSignal.initWithContext(this@TrendingActivity)
        //OneSignal.setAppId(“”);
        OneSignal.promptForPushNotifications();

        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        binding.progress.visible()
        apiCalling()
        initClick()
    }

    private fun initClick() {
        binding.apply {
            groupSearch.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@TrendingActivity) {
                    startActivity(Intent(this@TrendingActivity, Search_Activity::class.java))
                }
            }
            groupProgress.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@TrendingActivity) {
                    startActivity(Intent(this@TrendingActivity, ProgressActivity::class.java))
                }
            }
            groupStorage.setAllOnClickListener {
                AdsManager.getInstance().showInterstitialAd(this@TrendingActivity) {
                    startActivity(Intent(this@TrendingActivity, StorageActivity::class.java))
                }
            }
            imgback.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@TrendingActivity) { finish() }
            }
        }
    }

    private fun apiCalling() {
        val bodyModel = TrendingListBodyModel(
            browseId = "FEtrending", context = Context(
                request = Request(
                    internalExperimentFlags = emptyList(), useSsl = true
                ), client = Client(
                    hl = "en-GB",
                    gl = "IN",
                    clientName = "WEB",
                    originalUrl = "https://www.youtube.com",
                    clientVersion = "2.20230803.01.00",
                    platform = "DESKTOP"
                ), user = User(lockedSafetyMode = false)
            ), params = "4gIOGgxtb3N0X3BvcHVsYXI%3D"
        )

        val apiInterface = getRetrofit().create(TrendingInterface::class.java)
        apiInterface.trendingData(bodyModel).enqueue(object : Callback<TrendingListModel> {

            override fun onResponse(
                call: Call<TrendingListModel>, response: Response<TrendingListModel>
            ) {
                if (response.isSuccessful) {
                    "is successful".log()
                    binding.progress.gon()
                    list = response.body()!!.contents!!.twoColumnBrowseResultsRenderer!!.tabs!![0]!!.tabRenderer!!.content!!.sectionListRenderer!!.contents!![0]!!.itemSectionRenderer!!.contents!![0]!!.shelfRenderer!!.content!!.expandedShelfContentsRenderer!!.items as ArrayList<ItemsItem>

                    binding.trendingRv.adapter = TrendingAdapter(this@TrendingActivity, list)

                    if (arePermissionsGranted()) {
                        "is granted".log()
                    } else {
                        requestPermissionYt()
                    }


                    if (response.body()!!.equals(null)) {
                        "response null".log()
                    } else {
                        "Response: ${response.body()!!.contents!!.twoColumnBrowseResultsRenderer!!.tabs!![0]!!.tabRenderer!!.content!!.sectionListRenderer!!.contents!![0]!!.itemSectionRenderer!!.contents!![0]!!.shelfRenderer!!.content!!.expandedShelfContentsRenderer!!.items!!.size}".log()
                    }
                }
            }

            override fun onFailure(call: Call<TrendingListModel>, t: Throwable) {
                "error: ${t.message}".log()
            }
        })
    }

    private fun requestPermissionYt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.WRITE_SETTINGS
                ), WRITE_PERMISSIONS
            )
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
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
                        this, permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        } else {
            val permissions: Array<String> =
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO)
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        this, permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ("Permission Granted...").log()
            } else {
                ("Permission Denied...").log()
            }
        }
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@TrendingActivity) { finish() }
    }
}