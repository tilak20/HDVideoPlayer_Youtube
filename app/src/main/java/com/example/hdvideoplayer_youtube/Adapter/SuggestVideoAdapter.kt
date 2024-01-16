package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hdvideoplayer_youtube.Activity.YoutubeVideoActivity
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.ResultsItem
import com.example.hdvideoplayer_youtube.Utils.loadImg
import com.example.hdvideoplayer_youtube.databinding.TrendingItemBinding
import com.google.ads.sdk.AdsManager

class SuggestVideoAdapter(
    var activity: Activity, private var list: ArrayList<ResultsItem>, var onClick: () -> Unit
) : RecyclerView.Adapter<SuggestVideoAdapter.TrendingVH>() {

    private lateinit var videoId: String

    class TrendingVH(var binding: TrendingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrendingVH(
        TrendingItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TrendingVH, position: Int) {
        holder.binding.apply {

            val item = list[position]

//            list.size.log()

            if (item.compactVideoRenderer != null) {
                txtTitle.isSelected = true
                txtTitle.text = item.compactVideoRenderer.title!!.simpleText
//                txtTime.text = item.compactVideoRenderer.publishedTimeText!!.simpleText
                txtTime.text = if (item.compactVideoRenderer.publishedTimeText == null) {
                    ""
                } else {
                    item.compactVideoRenderer.publishedTimeText.simpleText
                }
                txtViews.text =
                    if (item.compactVideoRenderer.shortViewCountText!!.simpleText == null) {
                        item.compactVideoRenderer.shortViewCountText.runs!![0]!!.text
                    } else {
                        item.compactVideoRenderer.shortViewCountText.simpleText
                    }
//                txtDuration.text = item.compactVideoRenderer.lengthText!!.simpleText
                txtDuration.text = if (item.compactVideoRenderer.lengthText == null) {
                    ""
                } else {
                    item.compactVideoRenderer.lengthText.simpleText
                }
                imageFilterView.loadImg(item.compactVideoRenderer.thumbnail!!.thumbnails!![0]!!.url!!)

                videoId = item.compactVideoRenderer.videoId.toString()
            }

            root.setOnClickListener {
                onClick()

                AdsManager.getInstance().showInterstitialAd(activity) {
                    activity.startActivity(
                        Intent(
                            activity, YoutubeVideoActivity::class.java
                        ).putExtra("videoId", item.compactVideoRenderer!!.videoId).putExtra(
                            "videoTime", item.compactVideoRenderer.lengthText!!.simpleText
                        )
                    )
                }
            }
        }
    }
}