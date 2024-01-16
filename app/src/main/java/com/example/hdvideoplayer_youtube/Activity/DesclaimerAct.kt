package com.example.hdvideoplayer_youtube.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.databinding.ActivityDesclaimerBinding

class DesclaimerAct : BaseAct<ActivityDesclaimerBinding>() {

    var granted: Boolean = false
    lateinit var permissions: Array<String>
    val READ_PERMISSIONS = 1
    val WRITE_PERMISSIONS = 2

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityDesclaimerBinding.inflate(layoutInflater)

    override fun initUI() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions = arrayOf(
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.WRITE_SETTINGS
            )
        }

        requestPermissions13()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            granted = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                this, permissions[0]
            )
        }
        binding.apply {
            btnNext.setOnClickListener {
                if (!granted) {
                    requestPermissions13()
                }
                startActivity(Intent(this@DesclaimerAct, MainActivity::class.java))
            }
            btnDownload.setOnClickListener {
                if (!granted) {
                    requestPermissions13()
                }
                startActivity(Intent(this@DesclaimerAct, MainActivity::class.java))
            }
        }
    }

    fun requestPermissions13() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            requestPermissions(permissions, 100)

            Log.d("FATZ", "Android - 13 = 1 ")
        } else {
            requestPermission()
            Log.d("FATZ", "Android - 13 = 2")
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


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ("Granted...").log()
            } else {
                ("Not Granted...").log()
            }
        }
    }
}