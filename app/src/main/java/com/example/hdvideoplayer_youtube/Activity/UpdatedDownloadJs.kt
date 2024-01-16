package com.example.hdvideoplayer_youtube.Activity

import android.webkit.JavascriptInterface
import com.example.hashtagapi.Utils.WEBHOST
import com.example.hashtagapi.Utils.WEBURL
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log

class ADAPTATION_HOLDER(
    private val webUrl: String = "",
    private inline val onGetting: (String, String) -> Unit
) {
    @JavascriptInterface
    fun receiveJsParseResult(result: String) {
        (result).log()
        onGetting.invoke("receiveJsParseResult", result)
    }
    @JavascriptInterface
    fun clickDownloadButtonEvent(host: String) {
        ("clickDownloadButtonEvent: $host").log()
        onGetting.invoke("clickDownloadButtonEvent", host)
    }
    @JavascriptInterface
    fun setParseBtnType(str1: String, str2: String, str3: String) {
        ("setParseBtnType: $str1 | $str2 | $str3 ").log()
        onGetting.invoke("setParseBtnType", str2)
    }
    @JavascriptInterface
    fun reportXnxxParseFail2GoogleForm(str1: String, str2: String, str3: String) {
        ("reportXnxxParseFail2GoogleForm: $str1 | $str2 | $str3 ").log()
    }
    @JavascriptInterface
    fun warn(str1: String, str2: String) {
        ("warn: $str1 | $str2 ").log()
    }
    @JavascriptInterface
    fun reportAdapterRule(str1: String) {
        ("reportAdapterRule: $str1 ").log()
    }
    @JavascriptInterface
    fun adapterUniteCallback(str1: String) {
        ("adapterUniteCallback: $str1 ").log()
    }
    @JavascriptInterface
    fun userChangePage(str1: String) {
        ("userChangePage: $str1 ").log()
    }
    @JavascriptInterface
    fun startDetectWebsite(str1: String) {
        ("startDetectWebsite: $str1 ").log()
    }
    @JavascriptInterface
    fun adapterWebsiteParseFail(str1: String) {
        ("adapterWebsiteParseFail: $str1 ").log()
    }
    @JavascriptInterface
    fun loadLocalJsFile(str1: String) {
        ("loadLocalJsFile: $str1 ").log()
        if (str1.isNotEmpty())
            onGetting.invoke("loadLocalJsFile", str1)
    }
    @JavascriptInterface
    fun getWebUrl(): String {
        ("getWebUrl: $webUrl").log()
        return WEBURL
    }
    @JavascriptInterface
    fun getHost(): String {
        ("getHost: $webUrl").log()
        return WEBHOST
    }
}