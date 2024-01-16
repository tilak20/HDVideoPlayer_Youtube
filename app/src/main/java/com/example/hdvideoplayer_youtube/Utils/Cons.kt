package com.example.hashtagapi.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.lifecycle.MutableLiveData
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.onBackground
import com.example.hdvideoplayer_youtube.ModelData.DownloadModel
import com.example.hdvideoplayer_youtube.ModelData.Downloading
import java.io.*
import java.text.DecimalFormat
import java.util.*
import kotlin.math.log10
import kotlin.math.pow

const val YTAPI = "YTAPI"
const val SAVE_FOLDER_NAME_FOR_INSTA = "/Download/${YTAPI}"
const val ISLOGIN = "isLogin"
const val SEARCH_HISTORY_KEY = "search_history"
const val ISALLOWTOWRITE = "isAllow"
const val OPEN_DOCOMENT_TREE_REQUEST_CODE = 10
const val MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 123
const val OPEN_DOCOMENT_TREE_REQUEST_CODE_BUSINESS = 1023
const val SAVE_FOLDER_NAME_FOR_WHATSAPP = "/Download/Social_Video_Downloader/whatsapp/"
const val PREFRENCE_CLASS = "pref_class"
const val WEBURL = ""
const val WEBHOST = ""
const val READ_PERMISSIONS = 1
const val WRITE_PERMISSIONS = 2


val androidWeb =
    "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36"

const val FOLDER_NAME = "/WhatsApp/"
const val FOLDER_NAME_Whatsappbusiness = "/WhatsApp Business/"
const val FOLDER_NAME_Whatsapp_and11 = "/Android/media/com.whatsapp/WhatsApp/"
const val FOLDER_NAME_Whatsapp_and11_B = "/Android/media/com.whatsapp.w4b/WhatsApp Business/"

const val directoryInstaShoryDirectorydownload_videos_facebook =
    "/Social_Video_Downloader/Facebook/"

const val DlApisUrl: String = "http://45.77.253.213:9191/api/info?url="
const val directoryTwitterDirectorydownload_videos = "/Social_Video_Downloader/Twitter/"
const val directoryFacebookDirectorydownload_videos = "/Social_Video_Downloader/Facebook/"
//val COOKIE = ApplicationClass.getString("COOKIE")!!

const val directoryInstaShoryDirectorydownload_videos = "/Social_Video_Downloader/Instagram/"
val list = ArrayList<DownloadModel>()
var mulaList: MutableLiveData<ArrayList<Downloading>> = MutableLiveData()
var listofdownloadmodel: ArrayList<Downloading> = arrayListOf()
var mulaVideoList: MutableLiveData<ArrayList<File>> = MutableLiveData()
var listofVideo: ArrayList<File> = arrayListOf()
var latestVideo: MutableLiveData<ArrayList<DownloadModel>> = MutableLiveData()


@JvmField
val UserAgentsListLogin = arrayOf(
    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36"
)

@SuppressLint("SimpleDateFormat")
fun getOutputMediaFile(node: String): File? {
    // To be safe, you should check that the SDCard is mounted
    // using Environment.getExternalStorageState() before doing this.
    val mediaStorageDir = File(
        Environment.getExternalStorageDirectory().absolutePath + SAVE_FOLDER_NAME_FOR_INSTA
    )

    if (!mediaStorageDir.exists()) {
        if (!mediaStorageDir.mkdirs()) {
            return null
        }
    }
    // Create a media file name
    val mediaFile: File
    val mImageName =
        "${if (node == "Insta") "insta_" else if (node == "FB") "fb_" else "saver_"}${(System.currentTimeMillis() / 1000)}.png"
    mediaFile = File(mediaStorageDir.path + File.separator + mImageName)
    return mediaFile
}

fun storeImage(image: Bitmap, node: String) {
    val pictureFile: File = getOutputMediaFile(node)!!
    try {
        val fos = FileOutputStream(pictureFile)
        image.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.close()
    } catch (e: FileNotFoundException) {
        ("Error File Not Found: " + e.message).log()
    } catch (e: IOException) {
        ("Error accessing file: " + e.message).log()
    }
}

fun formatFileSize(sizeInBytes: Long): String {
    val df = DecimalFormat("#.##")
    return when {
        sizeInBytes < 1000 -> "$sizeInBytes B"
        sizeInBytes < 1000 * 1000 -> "${df.format(sizeInBytes.toDouble() / 1024)} KB"
        sizeInBytes < 1000 * 1000 * 1000 -> "${df.format(sizeInBytes.toDouble() / (1024 * 1024))} MB"
        else -> "${df.format(sizeInBytes.toDouble() / (1024 * 1024 * 1024))} GB"
    }
}


fun getFileSize(size: Long): String {
    if (size <= 0) return "0"
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
    return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
}

fun scanMediaFile(context: Context, path: String?) {
    path?.let {
        val paths = arrayOf(path)
        MediaScannerConnection.scanFile(context, paths, null, null)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun hasInternetConnect(activity: Activity): Boolean {
    var isWifiConnected = false
    var isMobileConnected = false
    val cm = activity.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    if (cm.defaultProxy != null) return false

    for (ni in cm.allNetworkInfo) {
        if (ni.typeName.equals("WIFI", ignoreCase = true)) if (ni.isConnected) isWifiConnected =
            true
        if (ni.typeName.equals("MOBILE", ignoreCase = true)) if (ni.isConnected) isMobileConnected =
            true
    }

    return isWifiConnected || isMobileConnected
}

fun getRandomNumber(bound: Int) = Random().nextInt(bound)

fun shareFile(activity: Activity, item: String) {
    ("Item: $item").log()
    val share = Intent(Intent.ACTION_SEND)
    share.type = "${if (item.endsWith(".png") || item.endsWith(".jpg")) "image" else "video"}/*"
    share.putExtra(
        Intent.EXTRA_STREAM, FileProvider.getUriForFile(
            activity,
            activity.packageName + ".provider",
            File(item),
            item.lowercase(Locale.getDefault())
        )
    )
    share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    activity.startActivity(
        Intent.createChooser(
            share,
            "Share ${if (item.endsWith(".png") || item.endsWith(".jpg")) "Image" else "Video"}"
        )
    )
}

fun shareImageWhatsApp(activity: Activity, item: String, packageName: String?, apkname: String) {
    val share = Intent(Intent.ACTION_SEND)
    share.type = "${if (item.endsWith(".png") || item.endsWith(".jpg")) "image" else "video"}/*"
    share.putExtra(
        Intent.EXTRA_STREAM, FileProvider.getUriForFile(
            activity,
            activity.packageName + ".provider",
            File(item),
            item.lowercase(Locale.getDefault())
        )
    )
    share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    if (appInstalledOrNot(packageName!!, activity)) {
        share.setPackage(packageName)
        activity.startActivity(
            Intent.createChooser(
                share,
                "Share ${if (item.endsWith(".png") || item.endsWith(".jpg")) "Image" else "Video"}"
            )
        )
    } else {
        "File Path = $item".log()
    }
}

private fun appInstalledOrNot(uri: String, activity: Activity): Boolean {
    val pm: PackageManager = activity.packageManager
    try {
        pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        print(e)
    }
    return false
}

fun makeFolder() {
    onBackground {
        val file_root = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + YTAPI
        )
        if (!file_root.exists()) {
            "Make Dir".log()
            file_root.mkdir()
        }

        val file_v = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + YTAPI
        )
        if (!file_v.exists()) {
            "Make Dir".log()
            file_v.mkdir()
        }

        val file_t = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + YTAPI
        )
        if (!file_t.exists()) {
            "Make Dir".log()
            file_t.mkdir()
        }
        val file_i = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + YTAPI
        )
        if (!file_i.exists()) {
            "Make Dir".log()
            file_i.mkdir()
        }
    }
}

fun getjs(activity: Activity, str: String?, canAdd: Boolean = false): String {
    val open: InputStream
    try {

        if (str != "null") {
            open = activity.assets.open(str.toString())
            val bArr = ByteArray(open.available())
            open.read(bArr)
            val str2 = String(bArr)
            return try {
                open.close()
                str2
            } catch (unused: IOException) {
                str2
            }
        }
    } catch (unused2: IOException) {
        return ""
    } catch (th: Throwable) {
        th.addSuppressed(th)
    }
    return ""
}
