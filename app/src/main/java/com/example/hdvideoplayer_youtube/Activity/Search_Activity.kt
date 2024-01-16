package com.example.hdvideoplayer_youtube.Activity

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.downloadersocial.fastvideodownloader.ApiClient.YoutubeClient.Companion.getRetrofit
import com.downloadersocial.fastvideodownloader.ApiClient.YoutubeClient.Companion.getSuggationRetrofit
import com.downloadersocial.fastvideodownloader.ApiInterface.TrendingInterface
import com.downloadersocial.fastvideodownloader.Model.SearchModel.Client
import com.downloadersocial.fastvideodownloader.Model.SearchModel.ContentsItem
import com.downloadersocial.fastvideodownloader.Model.SearchModel.Request
import com.downloadersocial.fastvideodownloader.Model.SearchModel.SearchContext
import com.downloadersocial.fastvideodownloader.Model.SearchModel.SearchModel
import com.downloadersocial.fastvideodownloader.Model.SearchModel.SearchModellist
import com.downloadersocial.fastvideodownloader.Model.SearchModel.User
import com.example.hashtagapi.Utils.hasInternetConnect
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.setAllOnClickListener
import com.example.hdvideoplayer_youtube.Adapter.SearchAdapter
import com.example.hdvideoplayer_youtube.Adapter.SuggestionAdapter
import com.example.hdvideoplayer_youtube.R

import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.toast
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.ActivitySearchBinding
import com.google.ads.sdk.AdsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Search_Activity : BaseAct<ActivitySearchBinding>() {

    val handler = Handler(Looper.getMainLooper())
    lateinit var name: String
    lateinit var searchAdapter: SearchAdapter
    var list: ArrayList<ContentsItem> = arrayListOf()
    val list1: ArrayList<ContentsItem> = arrayListOf()
    val client = "youtube"
    val ds = "yt"
    val gl = "US"
    val xhr = "t"

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivitySearchBinding.inflate(layoutInflater)

    override fun initUI() {
        binding.apply {
            AdsManager.getInstance().showNativeSmall(nativeads, R.layout.ad_unified)

            imgback.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@Search_Activity) { finish() }
            }
            groupTrending.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@Search_Activity) {
                    startActivity(Intent(this@Search_Activity, TrendingActivity::class.java))
                    finish()
                }
            }
            groupStorage.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@Search_Activity) {
                    startActivity(Intent(this@Search_Activity, StorageActivity::class.java))
                    finish()
                }
            }
            groupProgress.setAllOnClickListener {

                AdsManager.getInstance().showInterstitialAd(this@Search_Activity) {
                    startActivity(Intent(this@Search_Activity, ProgressActivity::class.java))
                    finish()
                }
            }
            btnsearch.setOnClickListener {
                performSearch()
                suggestionRv.gon()
                searchRv.visible()
            }

            txtnews.setOnClickListener { tagSearch("Today News") }
            txtlovesong.setOnClickListener { tagSearch("Love Songs") }
            txtdance.setOnClickListener { tagSearch("Sexy Dance") }
            txtdeepwork.setOnClickListener { tagSearch("Deep Work") }
            txtnature.setOnClickListener { tagSearch("Nature") }
            txttiktok.setOnClickListener { tagSearch("Hot TikTok") }
            txtrelax.setOnClickListener { tagSearch("Relaxing") }
            txtchill.setOnClickListener { tagSearch("Chill") }
            edtSearch.setOnEditorActionListener { _, actionId, _ ->
                if (hasInternetConnect(this@Search_Activity)) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        performSearch()
                        suggestionRv.gon()
                        searchRv.visible()
                    }
                } else {
                    ("Internet connection error").toast(this@Search_Activity)
                }
                true
            }

            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                    // This method is called before the text changes.
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // This method is called when the text changes.
                    val query = s.toString()

                    if (start == -1 || query.isEmpty()) {
                        handler.postDelayed({
                            group3.visible()
                            suggestionRv.gon()
                            "qurery is empty".log()
                        }, 500)
                    } else {
                        group3.gon()
                        suggestionRv.visible()
                        "qurery is not empty".log()
                    }
//                    suggestionRv.visible()
                    searchRv.gon()
                    suggationApiCall(query)
                }

                override fun afterTextChanged(s: Editable?) {
                    // This method is called after the text changes.
                }
            })
        }

    }

    fun tagSearch(tag: String) {
        AdsManager.getInstance().showInterstitialAd(this@Search_Activity) {
            binding.apply {
                edtSearch.setText(tag)
                binding.progress.visibility = View.VISIBLE
                searchApiCall(tag)
                val view: View? = this@Search_Activity.currentFocus
                if (view != null) {
                    val inputMethodManager =
                        this@Search_Activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
                searchRv.visible()
                suggestionRv.gon()
            }
        }
    }

    fun performSearch() {
        AdsManager.getInstance().showInterstitialAd(this@Search_Activity) {
            name = binding.edtSearch.text.toString()

            if (name.isNotEmpty()) {
                binding.progress.visibility = View.VISIBLE
                searchApiCall(name)
            } else {
                "search is empty".toast(this@Search_Activity)
            }
            val view: View? = this@Search_Activity.currentFocus
            if (view != null) {
                val inputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    fun suggationApiCall(query: String) {

        val apiInterface = getSuggationRetrofit().create(TrendingInterface::class.java)
        apiInterface.suggationData(client, ds, gl, query, xhr)
            .enqueue(object : Callback<List<Any>> {
                override fun onResponse(call: Call<List<Any>>, response: Response<List<Any>>) {

                    if (response.isSuccessful) {
                        val list = response.body()!![1]

                        binding.suggestionRv.adapter = SuggestionAdapter(
                            this@Search_Activity, list as List<List<Any>>
                        ) { position ->
                            binding.progress.visible()
                            val item = list[position]
                            val dataitem = item[0].toString()
                            binding.edtSearch.setText(dataitem)
                            binding.searchRv.visible()
                            binding.suggestionRv.gon()
                            searchApiCall(dataitem)

                        }
                    }
                }

                override fun onFailure(call: Call<List<Any>>, t: Throwable) {
                    "Faile".log()
                }
            })
    }

    fun searchApiCall(query: String) {
        val searchmodel = SearchModel(
            query = query, context = SearchContext(
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
            )
        )

        val apiInterface = getRetrofit().create(TrendingInterface::class.java)
        apiInterface.searchData(bodyModel = searchmodel)
            .enqueue(object : Callback<SearchModellist> {
                override fun onResponse(
                    call: Call<SearchModellist>, response: Response<SearchModellist>
                ) {
                    if (response.isSuccessful) {
                        binding.progress.gon()
                        list =
                            response.body()!!.contents!!.twoColumnSearchResultsRenderer!!.primaryContents!!.sectionListRenderer!!.contents!![0]!!.itemSectionRenderer!!.contents as ArrayList<ContentsItem>
                        list1.clear()

                        list.forEach {

                            if (it.videoRenderer != null) {
//                                "here = ${it.videoRenderer}".log()
                                if (it.videoRenderer.viewCountText!!.runs == null) {
                                    if (it.videoRenderer.lengthText == null) {
                                        "video not available".log()
//                                        "video not available".toast(this@Search_Activity)
                                    } else {
//                                        if (it.videoRenderer.badges!![0] != null) {
//                                            if (it.videoRenderer.badges[0]!!.metadataBadgeRenderer!!.label == "LIVE") {
                                        list1.add(it)
//                                            } else {
//                                                "is live".log()
//                                            }
//                                        }else{
//                                            "is null".log()
//                                        }
                                    }

                                } else {
                                    "byy".log()
                                }
                            }

                        }
                        searchAdapter = SearchAdapter(this@Search_Activity, list1)
                        binding.searchRv.adapter = searchAdapter

//                        val lastPosition = searchAdapter.itemCount
//                        "list: ${list1.size} || lp: $lastPosition".log()
//                        if (list1.size == lastPosition) {
//
//                        }

                        binding.searchRv.addOnScrollListener(object :
                            RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)

                                val layoutManager =
                                    recyclerView.layoutManager as LinearLayoutManager
                                val lastVisibleItemPosition =
                                    layoutManager.findLastVisibleItemPosition()
                                val totalItemCount = searchAdapter.itemCount

                                if (lastVisibleItemPosition == totalItemCount - 1) {
                                    "is last".log()
//                                    searchApi()
                                }
                            }
                        })
                    }
                }

                override fun onFailure(call: Call<SearchModellist>, t: Throwable) {
                    "Fail".log()
                }
            })
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@Search_Activity) { finish() }

    }
}