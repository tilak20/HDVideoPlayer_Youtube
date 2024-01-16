package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hdvideoplayer_youtube.Activity.YoutubeVideoActivity
import com.example.hdvideoplayer_youtube.ModelData.ItemsItem
import com.example.hdvideoplayer_youtube.Utils.loadImg
import com.example.hdvideoplayer_youtube.databinding.YoutubeVideoItemBinding
import com.google.ads.sdk.AdsManager

class TrendingAdapter(var activity: Activity, private var list: ArrayList<ItemsItem>) :
    RecyclerView.Adapter<TrendingAdapter.TrendingVH>() {
    var thumbnail = ""

    class TrendingVH(var binding: YoutubeVideoItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrendingVH(YoutubeVideoItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TrendingVH, position: Int) {
        holder.binding.apply {

            val item = list[position]

            txtTitle.isSelected = true
            txtTitle.text = item.videoRenderer!!.title!!.runs?.get(0)!!.text
            txtTime.text = item.videoRenderer.publishedTimeText!!.simpleText
            txtViews.text = item.videoRenderer.shortViewCountText!!.simpleText
            txtDuration.text = item.videoRenderer.lengthText!!.simpleText

            thumbnail = item.videoRenderer.thumbnail!!.thumbnails!!.last()!!.url!!
            imageFilterView.loadImg(thumbnail)

            val videoId = item.videoRenderer.videoId

            root.setOnClickListener {

                AdsManager.getInstance().showInterstitialAd(activity) {
                    activity.startActivity(
                        Intent(activity, YoutubeVideoActivity::class.java).putExtra(
                            "videoId", videoId
                        ).putExtra("videoTime", item.videoRenderer.lengthText.simpleText)
                    )
                }
            }
        }
    }
}