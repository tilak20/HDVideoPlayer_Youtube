package com.example.hdvideoplayer_youtube.Main

import DetectImageDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hashtagapi.Utils.androidWeb
import com.example.hashtagapi.Utils.getjs
import com.example.hdvideoplayer_youtube.Activity.ADAPTATION_HOLDER
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.ProgressActivity
import com.example.hdvideoplayer_youtube.Dialog.DetectVideoDialog
import com.example.hdvideoplayer_youtube.Dialog.NotDetectionDialog
import com.example.hdvideoplayer_youtube.ModelData.AhaModel
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.SearchEngine.SearchEngineManager
import com.example.hdvideoplayer_youtube.SearchEngine.SearchEngineModule
import com.example.hdvideoplayer_youtube.Services.OnBoundedDragTouchListener
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.load_status
import com.example.hdvideoplayer_youtube.Utils.visible
import com.google.gson.Gson

class BrowseActivity : AppCompatActivity() {

    lateinit var searchEngineManager: SearchEngineManager
    private var webViewVisible = false
    private var videoRadioButtonChecked = false

    var currenturl: String? = null
    var item: AhaModel? = null
    var url: String? = null
    lateinit var javascript: String
    var fileSize: String = ""

    lateinit var from: String
    lateinit var key: String

    private val mainWebView: MainWebView by bind(R.id.activity_main_web_view)
    private val progress: ProgressBar by bind(R.id.activity_main_progress)
    private val imgback: ImageFilterView by bind(R.id.imgback)
    private val imgClose: ImageFilterView by bind(R.id.imgClose)
    private val imagerela: RelativeLayout by bind(R.id.imagerela)
    private val rlDownloaded: ImageFilterView by bind(R.id.rlDownloaded)
    private val tvimagesize: TextView by bind(R.id.tvimagesize)
    private val swipeRefreshLayout: SwipeRefreshLayout by bind(R.id.swipeRefreshLayout)
    private val imageimage: AppCompatImageView by bind(R.id.imageimage)

    private val browserWebViewListener = createBrowserWebViewListener()

    private var forceDestroy = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchEngineManagerInternal by lazy { SearchEngineModule().createSearchEngineManager() }

        searchEngineManager = searchEngineManagerInternal

        setContentView(R.layout.activity_browse)

        mainWebView.browserWebViewListener = browserWebViewListener
        mainWebView.setBackgroundColor(Color.TRANSPARENT)

        getIntentData()

        imgback.setOnClickListener {
            if (webViewCanGoBack()) {
                webViewBack()
            } else {
                finish()
            }
        }

        imgClose.setOnClickListener {
            finish()
        }

        val firstActivityLaunch = savedInstanceState == null
        if (firstActivityLaunch) {
            setWebViewVisible(webViewVisible)
        }
        imagerela.gon()
        rlDownloaded.gon()
        tvimagesize.gon()
        swipeRefreshLayout.setOnRefreshListener {
            mainWebView.loadUrl(currenturl.toString())
            swipeRefreshLayout.isRefreshing = false
        }
        mainWebView.settings.javaScriptEnabled = true
        if (from == "Search") {
            performSearch("https://www.google.com/search?q=${key}")
            "Search = 2".log()
        } else {
            performSearch(key)
            "Search = 1".log()
        }

        mainWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        mainWebView.settings.userAgentString = androidWeb
        mainWebView.addJavascriptInterface(ADAPTATION_HOLDER { from, res ->
            runOnUiThread {
                when (from.trim()) {
                    "loadLocalJsFile" -> {
                        load_status = res
                    }

                    "setParseBtnType" -> {
                        "nativeBtn".log()
                        imagerela.visibility = if (res == "nativeBtn") VISIBLE else GONE
                        rlDownloaded.visibility = if (res == "nativeBtn") VISIBLE else GONE
                    }

                    "receiveJsParseResult" -> {
                        item = Gson().fromJson(res, AhaModel::class.java)

                        if (imagerela.visibility == GONE) {
                            if (item!!.parseType == "jpg" || item!!.parseType == "png") DetectImageDialog(
                                this@BrowseActivity, item!!
                            )
                            else DetectVideoDialog(this@BrowseActivity, item!!)
                        } else {
                            if (item!!.dataList!!.isEmpty()) {
                                imagerela.gon()
                                rlDownloaded.gon()
                                tvimagesize.gon()
                            } else {
                                tvimagesize.visible()
                            }
                            imageimage.setBackgroundColor(Color.parseColor("#FF274AC6"))
                        }
                    }

                    "clickDownloadButtonEvent" -> {
                    }
                }
            }
        }, "ADAPTATION_HOLDER")

        val drag = OnBoundedDragTouchListener(imagerela, mainWebView)
        drag.setOnDragActionListener(object : OnBoundedDragTouchListener.OnDragActionListener {
            override fun onDragStart(view: View) {
            }

            override fun onDragEnd(view: View) {
                if (!tvimagesize.isVisible) {
                    ("No Detected").log()
                    NotDetectionDialog(this@BrowseActivity)
                } else {
                    if (item != null) DetectVideoDialog(this@BrowseActivity, item!!)
                }
            }
        })
        imagerela.setOnTouchListener(drag)

        val drag1 = OnBoundedDragTouchListener(rlDownloaded, mainWebView)
        drag1.setOnDragActionListener(object : OnBoundedDragTouchListener.OnDragActionListener {
            override fun onDragStart(view: View) {
            }

            override fun onDragEnd(view: View) {
                startActivity(Intent(this@BrowseActivity, ProgressActivity::class.java))
            }
        })

        rlDownloaded.setOnTouchListener(drag1)
        mainWebView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (mainWebView.progress >= 100) {
                    hideLoader()
                } else {
                    showLoader(mainWebView.progress)
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (mainWebView.progress >= 100) {
                    hideLoader()
                } else {
                    showLoader(mainWebView.progress)
                }

                if (load_status.isNotEmpty()) {
                    view!!.evaluateJavascript(getjs(this@BrowseActivity, "newJs/" + load_status.trim())) {
                        it.log()
                    }
                }

                when (from) {
                    "Instagram" -> {
                        javascript = "detect_ins.js"
                    }

                    "Facebook" -> {
                        javascript = "detect_fb.js"
                    }

                    "Twitter" -> {
                        javascript = "detect_tw.js"
                    }

                    "TikTok" -> {
                        javascript = "detect_tt.js"
                    }

                    "Vimeo" -> {
                        javascript = "vimeo.js"
                    }

                    "Dailymotion" -> {
                        javascript = "dailymotion_parser.js"
                    }

                    "Google" -> {
                        javascript = "null"
                    }

                    "Search" -> {
                        javascript = if (key.contains("instagram")) {
                            "detect_ins.js"
                        } else if (key.contains("facebook")) {
                            "detect_fb.js"
                        } else if (key.contains("twitter")) {
                            "detect_tw.js"
                        } else if (key.contains("tikTok")) {
                            "detect_tt.js"
                        } else if (key.contains("vimeo")) {
                            "vimeo.js"
                        } else if (key.contains("Dailymotion")) {
                            "dailymotion_parser.js"
                        } else {
                            "null"
                        }
                    }

                    else -> {
                        javascript = "null"
                    }
                }

                view!!.evaluateJavascript(getjs(this@BrowseActivity, javascript)) {
                    ("String $it").log()
                    currenturl = mainWebView.url
                    key = mainWebView.url.toString()

                    javascript = if (key.contains("instagram")) {
                        "detect_ins.js"
                    } else if (key.contains("facebook")) {
                        "detect_fb.js"
                    } else if (key.contains("twitter")) {
                        "detect_tw.js"
                    } else if (key.contains("tikTok")) {
                        "detect_tt.js"
                    } else if (key.contains("vimeo")) {
                        "vimeo.js"
                    } else if (key.contains("Dailymotion")) {
                        "dailymotion_parser.js"
                    } else {
                        "null"
                    }

                    view.evaluateJavascript(getjs(this@BrowseActivity, javascript)) {}
                }
            }
        }
        mainWebView.webChromeClient = WebChromeClient()
    }

    fun getIntentData() {
        from = intent.getStringExtra("from").toString()
        key = intent.getStringExtra("key").toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (forceDestroy) {
            return
        }
        mainWebView.browserWebViewListener = null
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val url = intent?.extras?.getString(EXTRA_URL)
        if (url != null) {
            Log.d("FATZ", "url == 8 == ${url}")
            performSearch(url)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (forceDestroy) {
            return
        }
        outState.putBoolean("webViewVisible", webViewVisible)
        mainWebView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mainWebView.restoreState(savedInstanceState)
        webViewVisible = savedInstanceState.getBoolean("webViewVisible")
        setWebViewVisible(webViewVisible)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            if (webViewCanGoBack()) {
                webViewBack()
            } else {
                finish()
            }

            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun showUrl(url: String) {
        mainWebView.load(url)
        Log.d("FATZ", "search == 7 == ${url}")

    }

    fun webViewCanGoBack() = mainWebView.canGoBack()

    fun webViewBack() {
        mainWebView.goBack()
    }

    fun showLoader(progressPercent: Int) {
        progress.visibility = VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progress.setProgress(progressPercent, true)
        } else {
            progress.progress = progressPercent
        }
    }

    fun hideLoader() {
        progress.visibility = GONE
    }

    fun showWebView() {
        mainWebView.visibility = VISIBLE
    }

    fun hideWebView() {
        mainWebView.visibility = GONE
    }

    private fun createBrowserWebViewListener() = object : MainWebView.BrowserWebViewListener {
        override fun onPageFinished() {
            if (mainWebView.progress >= 100) {
                hideLoader()
            } else {
                showLoader(mainWebView.progress)
            }
        }

        override fun onProgressChanged() {

            if (mainWebView.progress >= 100) {
                hideLoader()
            } else {
                showLoader(mainWebView.progress)
            }
        }

        override fun onPageTouched() {
        }
    }

    private fun <T : View> bind(@IdRes res: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST") return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
    }

    companion object {
        private const val EXTRA_URL = "EXTRA_URL"
    }

    private fun performSearch(search: String) {
        val url = convertSearchToUrl(search)
        Log.d("FATZ", "search == 2 == $search | | $url")
        showUrl(url)
        setWebViewVisible(true)
    }

    private fun convertSearchToUrl(search: String) = if (videoRadioButtonChecked) {
        searchEngineManager.createSearchVideoUrl(search)
    } else {
        searchEngineManager.createSearchUrl(search)
    }

    private fun setWebViewVisible(visible: Boolean) {
        webViewVisible = visible
        if (visible) {
            showWebView()
            showLoader(0)
        } else {
            hideWebView()
            hideLoader()

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (mainWebView.canGoBack()) {
            mainWebView.goBack()
        } else finish()
    }

}
