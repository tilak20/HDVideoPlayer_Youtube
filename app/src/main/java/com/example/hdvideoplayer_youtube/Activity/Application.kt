package com.example.hdvideoplayer_youtube.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.constraintlayout.widget.Group
import com.example.hdvideoplayer_youtube.BuildConfig
import com.example.hdvideoplayer_youtube.Main.ApplicationGraph
import com.example.hdvideoplayer_youtube.ModelData.DataList
import com.example.hdvideoplayer_youtube.ModelData.Downloading
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.google.ads.sdk.AppManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Application : AppManager() {


    lateinit var gson: Gson

    @SuppressLint("WrongConstant")
    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences(SHARED_KEY, MODE_PRIVATE)
        editor = preferences.edit()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions = arrayOf(Manifest.permission.READ_MEDIA_VIDEO, Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.WRITE_SETTINGS)
        }

        setupApplicationGraph()

        // Debuggable WebView
        if (BuildConfig.DEBUG) {
            enableDebuggableWebView()
        }



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID, "Now Playing Song", NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "This is important channel for showing song"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }


    private fun setupApplicationGraph() {
        ApplicationGraph.init()
    }

    private fun enableDebuggableWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
                WebView.setWebContentsDebuggingEnabled(true)
            }
        }
    }

    companion object {
        var SHARED_KEY = "MusicShared"
        lateinit var preferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        val CHANNEL_ID = "channel1"
        val PLAY = "play"
        val NEXT = "next"
        val PREVIOUS = "previous"
        val CLOSE = "close"
        val OPEN = "open"
        var listofdownloadmodel: ArrayList<Downloading> = arrayListOf()
        val datalist: ArrayList<DataList> = arrayListOf()
        lateinit var permissions: Array<String>

        var currentVideoList = ArrayList<Video>()
        var videoList = arrayListOf<Video>()



        fun putString(key: String, value: String) {
            editor.putString(key, value).apply()
        }

        fun getString(key: String): String {
            return preferences.getString(key, "").toString()
        }

        fun putBoolean(key: String, value: Boolean) {
            editor.putBoolean(key, value).apply()
        }

        fun getBoolean(key: String): Boolean {
            return preferences.getBoolean(key, false)
        }

        fun putInt(key: String, value: Int) {
            editor.putInt(key, value).apply()
        }

        fun getInt(key: String, i: Int): Int {
            return preferences.getInt(key, i)
        }

        fun getVideoDuration(fileDuration: Int): String {
            val durationInMillis = fileDuration.toLong()
            val hours = TimeUnit.MILLISECONDS.toHours(durationInMillis)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis) % 60
            val seconds = TimeUnit.MILLISECONDS.toSeconds(durationInMillis) % 60
            return if (hours.toInt() == 0) {
                String.format("%02d:%02d", minutes, seconds)
            } else {
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }
        }

        fun Any?.log(): Unit = exc { Log.wtf("FATZ", "$this") }
        inline fun exc(block: () -> Unit) {
            try {
                block()
            } catch (e: Exception) {
                e.message
            }
        }

        fun onBackground(block: () -> Unit) {
            Executors.newSingleThreadExecutor().execute {
                block()
            }
        }

        fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
            referencedIds.forEach { id ->
                rootView.findViewById<View>(id).setOnClickListener(listener)
            }
        }

        fun convertUnixTimestampToDate(unixTimestamp: Long): String {
            val date = Date(unixTimestamp * 1000L) // Convert to milliseconds
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }

        fun convertUnixTimestampToMonth(unixTimestamp: Long): String {
            val date = Date(unixTimestamp * 1000L) // Convert to milliseconds
            val sdf = SimpleDateFormat("MM")
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }

        fun main(videoDateadded: String): String {
            val formattedDate = convertUnixTimestampToDate(videoDateadded.toLong())
            return formattedDate
        }

        fun Month(videoDateadded: String): String {
            val formattedMonth = convertUnixTimestampToMonth(videoDateadded.toLong())
            "month ${formattedMonth}".log()
            return formattedMonth
        }

        fun setRecentMap(recentMap: Map<String?, Video>?) {

            editor.putString("RecentMap", Gson().toJson(recentMap)).apply()

        }


        fun getRecentMap(): Map<String?, Video> {
            var map: Map<String?, Video>? = null
            try {
                val json: String = getString("RecentMap")
                map = Gson().fromJson(json, object : TypeToken<Map<String?, Video>>() {}.type)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return map ?: HashMap()
        }


    }


}