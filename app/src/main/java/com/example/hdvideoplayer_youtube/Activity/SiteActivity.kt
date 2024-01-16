package com.example.hdvideoplayer_youtube.Activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Environment
import android.os.Parcelable
import android.os.storage.StorageManager
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.documentfile.provider.DocumentFile
import com.example.hashtagapi.Utils.MY_PERMISSIONS_REQUEST_WRITE_STORAGE
import com.example.hashtagapi.Utils.OPEN_DOCOMENT_TREE_REQUEST_CODE
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Main.BrowseActivity
import com.example.hdvideoplayer_youtube.ModelData.WSMData
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivitySiteBinding
import com.google.ads.sdk.AdsManager
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.util.Locale
import java.util.Objects

class SiteActivity : BaseAct<ActivitySiteBinding>() {
    var permission = false
    var filesList: java.util.ArrayList<WSMData> = java.util.ArrayList<WSMData>()
    var isWhatsAppBusinessAvaliable = false
    var namedataprefs_business = ""
    var namedataprefs = ""
    lateinit var file: Uri

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivitySiteBinding.inflate(layoutInflater)

    override fun initUI() {

        namedataprefs = Application.getString("whatsapp")

        permission = Application.getBoolean("permission")

        if (permission) {
            try {
                setRV()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            ("permission: " + false).log()
        }

        initclick()


    }

    fun initclick() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        binding.apply {

            groupBrowser.setAllOnClickListener {
                startActivity(
                    Intent(this@SiteActivity, BrowseActivity::class.java).putExtra("from", "")
                        .putExtra("key", "")
                )
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

            }
            groupProgress.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@SiteActivity) {
                    startActivity(Intent(this@SiteActivity, ProgressActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

            groupStorage.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@SiteActivity) {
                    startActivity(Intent(this@SiteActivity, StorageActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

            imgback.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@SiteActivity) { finish() }

            }

            imgInstaReels.setOnClickListener {
                startIntent("Instagram", "http://www.instagram.com/accounts/login")
            }
            instaBg.setOnClickListener {
                startIntent("Instagram", "http://www.instagram.com/accounts/login")
            }
            facebookBg.setOnClickListener {
                startIntent("Facebook", "https://www.facebook.com/login")
            }

            twitterBg.setOnClickListener {
                startIntent("Twitter", "https://twitter.com/home")
            }

            tiktokBg.setOnClickListener {
                startIntent("TikTok", "https://www.tiktok.com")
            }

            vimeoBg.setOnClickListener {
                startIntent("Vimeo", "https://vimeo.com/watch")
            }

            googleBg.setOnClickListener {
                startIntent("Google", "https://www.google.com/")
            }

            dailymotionBg.setOnClickListener {
                startIntent("Dailymotion", "https://www.dailymotion.com/signin")
            }


            imgPaste.setOnClickListener {
                startIntent(
                    "Search",
                    textInputEditText.text.toString().trim().lowercase(Locale.getDefault())
                )
            }

            whatsappBg.setOnClickListener {
                permission = Application.getBoolean("permission")
                ("permission: $permission").log()
                if (permission) {
                    val gson = Gson()
                    val json = gson.toJson(filesList)

                    AdsManager.getInstance().showInterstitialAd(this@SiteActivity) {
                        startActivity(Intent(this@SiteActivity, WhatsAppActivity::class.java).putExtra("list", json))
                    }

                } else {
                    grantAndroid11StorageAccessPermission()
                }
            }

            binding.textInputEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startIntent(
                        "Search",
                        textInputEditText.text.toString().trim().lowercase(Locale.getDefault())
                    )
                }
                true
            }
        }
    }

    fun grantAndroid11StorageAccessPermission() {
        if (isMyPackedgeInstalled("com.whatsapp")) {
            val intent: Intent?
            val storageManager = getSystemService(STORAGE_SERVICE) as StorageManager
            val whatsappfolderdir: String = if (File(
                    Environment.getExternalStorageDirectory()
                        .toString() + "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses"
                ).isDirectory
            ) {
                ("whats == 1").log()
                "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses"
            } else {
                ("whats == 2").log()
                "WhatsApp%2FMedia%2F.Statuses"
            }
            if (Build.VERSION.SDK_INT >= 29) {
                intent = storageManager.primaryStorageVolume.createOpenDocumentTreeIntent()
                val scheme =
                    intent.getParcelableExtra<Parcelable>("android.provider.extra.INITIAL_URI")
                        .toString().replace("/root/", "/document/")
                val stringBuilder = "$scheme%3A$whatsappfolderdir"
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(stringBuilder))
            } else {
                intent = Intent("android.intent.action.OPEN_DOCUMENT_TREE")
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(whatsappfolderdir))
            }
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            startActivityForResult(intent, OPEN_DOCOMENT_TREE_REQUEST_CODE)
        } else {
            Toast.makeText(this, "WhatsApp is not installed", Toast.LENGTH_SHORT).show()
        }
    }

    fun setRV() {
        if (Application.getString("whatsapp") != "") {
            getData()
            (Application.getString("whatsapp") == "").log()
        } else if (Application.getString("whatsappbusiness") != "") {
            getData()
            ("hello == 2").log()
        } else {
            ("hello == 3").log()
            getData()
        }
//        ("Size: " + filesList.size + "  | String: " + namedataprefs).log()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OPEN_DOCOMENT_TREE_REQUEST_CODE && resultCode == -1) {
            val uri = data!!.data
            try {
                contentResolver.takePersistableUriPermission(
                    uri!!, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Application.putString("whatsapp", uri.toString())
            Application.putBoolean("permission", true)
            ("is granted").log()
            try {
                getData()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            Application.putBoolean("permission", false)
            ("is not Granted").log()
        }
    }

    @Throws(IOException::class)
    fun getData() {
        AsyncTask.execute {
            if (Build.VERSION.SDK_INT <= 29) {
                val fileslisttemp: Array<File> = getWhatsupFolder().listFiles()!!
                val allFiles = ArrayList(listOf(*fileslisttemp))
                ("getData : 5 " + allFiles.size).log()
                if (getWhatsupBusinessFolder().exists()) {
                    allFiles.addAll(
                        listOf(
                            *Objects.requireNonNull<Array<File>>(
                                getWhatsupBusinessFolder().listFiles()
                            )
                        )
                    )
                }
                allFiles.sortWith { f1, f2 ->
                    val diff = f2.lastModified() - f1.lastModified()
                    ("compare: 1 ==$diff").log()
                    if (diff > 0) {
                        1
                    } else if (diff < 0) {
                        -1
                    } else {
                        0
                    }
                }
                for (file1 in allFiles) {
                    if (file1!!.name != ".nomedia" && file.path != "") {
                        filesList.add(WSMData(file1.path, file1.name, file1.toString()))
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 30) {
                val allFiles = ArrayList<DocumentFile>()
                allFiles.clear()
                try {
                    val allFileswhatsapp: Array<DocumentFile> = getDataFromWhatsAppFolder()!!
                    if (isWhatsAppBusinessAvaliable) {
                        val allFilesbusiness: Array<DocumentFile> =
                            getDataFromWhatsAppBusinessFolder()!!
                        allFiles.addAll(listOf(*allFilesbusiness))
                    } else {
                        allFiles.addAll(listOf(*allFileswhatsapp))
                    }
//                    (": 5 " + allFiles.size).log()
                    for (allFile in allFiles) {
                        file = allFile.uri
                        //                            log( " 8 " + allFile.getName());
                        if (!allFile.uri.toString()
                                .contains(".nomedia") && allFile.uri.toString() != ""
                        ) {
                            if (allFile.uri.lastPathSegment != null) {
                                val lastPathSegment = allFile.uri.lastPathSegment
                                //                                    log("6 " + file.getPath() + " || " + allFile.getName() + " || " + file);
                            }

                            //                                Glide.with(HomeScreenActivity.this).load((allFile.getUri())).into(binding.image);
                            filesList.add(
                                WSMData(
                                    file.path.toString(), allFile.name.toString(), file.toString()
                                )
                            )
                        }
                    }
                } catch (e: java.lang.Exception) {
                    (e.message).log()
                }
            } else {
                checkPermission2()
            }
        }

    }


    fun getDataFromWhatsAppBusinessFolder(): Array<DocumentFile>? {
        try {
            val fromTreeUri = DocumentFile.fromTreeUri(this, Uri.parse(namedataprefs_business))
            if (fromTreeUri != null && fromTreeUri.exists() && fromTreeUri.isDirectory && fromTreeUri.canRead() && fromTreeUri.canWrite()) {
                return fromTreeUri.listFiles()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    fun checkPermission2() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                val alertBuilder = AlertDialog.Builder(this)
                alertBuilder.setCancelable(true)
                alertBuilder.setTitle("Permission necessary")
                alertBuilder.setMessage("Write Storage permission is necessary to Download Images and Videos!!!")
                alertBuilder.setPositiveButton(
                    "Yes"
                ) { dialog, which ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_WRITE_STORAGE
                    )
                }
                val alert = alertBuilder.create()
                alert.show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_STORAGE
                )
            }
        } else {
        }
    }

    fun getWhatsupBusinessFolder(): File {
        return if (File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "Android/media/com.whatsapp.w4b/WhatsApp Business" + File.separator + "Media" + File.separator + ".Statuses"
            ).isDirectory
        ) {
            File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "Android/media/com.whatsapp.w4b/WhatsApp Business" + File.separator + "Media" + File.separator + ".Statuses"
            )
        } else {
            File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "WhatsApp Business" + File.separator + "Media" + File.separator + ".Statuses"
            )
        }
    }

    fun getWhatsupFolder(): File {
        return if (File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "Android/media/com.whatsapp/WhatsApp" + File.separator + "Media" + File.separator + ".Statuses"
            ).isDirectory
        ) {
            ("whats == 1").log()
            File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "Android/media/com.whatsapp/WhatsApp" + File.separator + "Media" + File.separator + ".Statuses"
            )
        } else {
            ("whats == 2").log()
            File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "WhatsApp" + File.separator + "Media" + File.separator + ".Statuses"
            )
        }
    }

    fun getDataFromWhatsAppFolder(): Array<DocumentFile>? {
        return try {
            val fromTreeUri = DocumentFile.fromTreeUri(
                this, Uri.parse(Application.getString("whatsapp"))
            )!!
            fromTreeUri.listFiles()
        } catch (e: java.lang.Exception) {
            (" " + e.message).log()
            null
        }
    }


    fun startIntent(from: String, key: String) {
        startActivity(
            Intent(this, BrowseActivity::class.java).putExtra("from", from).putExtra("key", key)
        )
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@SiteActivity) { finish() }

    }

}