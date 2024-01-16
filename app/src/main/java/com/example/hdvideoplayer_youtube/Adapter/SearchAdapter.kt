package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.downloadersocial.fastvideodownloader.Model.SearchModel.ContentsItem
import com.example.hdvideoplayer_youtube.Activity.YoutubeVideoActivity
import com.example.hdvideoplayer_youtube.Utils.loadImg
import com.example.hdvideoplayer_youtube.databinding.TrendingItemBinding

class SearchAdapter(
    val activity: Activity,
    val list: ArrayList<ContentsItem>
) :
    RecyclerView.Adapter<SearchAdapter.SearchView>() {

    class SearchView(val binding: TrendingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchView(
        TrendingItemBinding.inflate(
            LayoutInflater.from(activity)
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SearchView, position: Int) {
        holder.binding.apply {

            if (list[position].videoRenderer == null) {
            } else {
                val item = list[position].videoRenderer!!

                txtTitle.isSelected = true
                txtTitle.text = item.title!!.runs?.get(0)!!.text

                txtTime.text =
                    if (item.publishedTimeText == null) "" else item.publishedTimeText.simpleText

                txtViews.text =
                    if (item.shortViewCountText == null) "" else item.shortViewCountText.simpleText
                txtDuration.text =
                    if (item.lengthText == null) "" else item.lengthText.simpleText

                imageFilterView.loadImg(item.thumbnail!!.thumbnails!![0]!!.url!!)

                val videoId = item.videoId
                root.setOnClickListener {
                    activity.startActivity(
                        Intent(
                            activity, YoutubeVideoActivity::class.java
                        ).putExtra("videoId", videoId).putExtra("videoTime",item.lengthText!!.simpleText)
                    )
                }
            }
        }
    }
}