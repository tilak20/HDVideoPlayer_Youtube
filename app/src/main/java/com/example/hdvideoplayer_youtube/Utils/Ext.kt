package com.example.hdvideoplayer_youtube.Utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.R
import com.google.gson.Gson
import java.util.concurrent.Executors

var WEBURL = ""
var WEBHOST = ""
var load_status: String = ""

var macOsWeb =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"
val androidWeb =
    "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36"

fun <T> T.toGson() = Gson().toJson(this)

fun <T> AppCompatActivity.toAc(java: Class<T>) = startActivity(Intent(this, java))


const val threshold = 1000
const val divisor = 1024F

fun Long.toSpeedString() = toTrafficString() + "/s"

fun Long.toTrafficString(): String {
    if (this == 0L)
        return "\t\t\t0\t  B"

    if (this < threshold)
        return "${this.toFloat().toShortString()}\t  B"

    val kib = this / divisor
    if (kib < threshold)
        return "${kib.toShortString()}\t KB"

    val mib = kib / divisor
    if (mib < threshold)
        return "${mib.toShortString()}\t MB"

    val gib = mib / divisor
    if (gib < threshold)
        return "${gib.toShortString()}\t GB"

    val tib = gib / divisor
    if (tib < threshold)
        return "${tib.toShortString()}\t TB"

    val pib = tib / divisor
    if (pib < threshold)
        return "${pib.toShortString()}\t PB"

    return "∞"
}

private fun Float.toShortString(): String {
    val s = "%.2f".format(this)
    if (s.length <= 4)
        return s
    return s.substring(0, 4).removeSuffix(".")
}

fun String.toast(activity: Activity) = Toast.makeText(activity, this, Toast.LENGTH_SHORT).show()



@SuppressLint("ResourceType")
fun ImageView.loadImg(link: Any) =
    Glide.with(this).load(link).placeholder(R.color.transparent).into(this)

fun htmlTagVertical(txt: String, value: Any): Spanned =
    "<font color=#064974><small>$txt</small></font> <br> <font>$value</font>".html()

fun htmlTagHorizontal(txt: String, value: Any): Spanned =
    "<font color=#969696><small>$txt</small></font>${value}".html()

fun TextView.underline() {
    this.paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gon() {
    this.visibility = GONE
}

fun ImageView.tint(any: Any?) {
    this.imageTintList =
        ColorStateList.valueOf(if (any is Int) any else Color.parseColor(any as String))
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.flipanim() {
    val animator: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat(View.ROTATION_Y, 0f, 360f)
    )
    animator.duration = 600
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.start()
}


fun View.flipanimdelay(delay: Long) {
    val animator: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat(View.ROTATION_Y, 0f, 360f)
    )
    animator.duration = delay
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.start()
}




fun <T> T.tos(ctx: Context) = Toast.makeText(ctx, "$this", Toast.LENGTH_SHORT).show()
fun <T> T.tosL(ctx: Context) = Toast.makeText(ctx, "$this", Toast.LENGTH_LONG).show()

fun String.html() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(
    this,
    Html.FROM_HTML_MODE_LEGACY
) else HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun disableBtn(v: View) {
    v.isEnabled = false
    Handler(Looper.getMainLooper()).postDelayed({ v.isEnabled = true }, 2000)
}

fun onBackground(block: () -> Unit) {
    Executors.newSingleThreadExecutor().execute {
        block()
    }
}

fun delayInMillis(millis: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        block()
    }, millis)
}

fun ImageView.load(any: Any?) {
//    Log.d("FFM", "Load Image Url: $any")
    Glide.with(this).load(any)
        .placeholder(R.drawable.splash_logo).into(this@load)

    this.visible()
}

fun openUri(ctx: Context, uri: String) =
    ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))

fun isServiceRunning(ctx: Context, serviceClass: Class<*>): Boolean {
    for (service in (ctx.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager).getRunningServices(
        Int.MAX_VALUE
    )) {
        if (serviceClass.name == service.service.className) return true
    }
    return false
}