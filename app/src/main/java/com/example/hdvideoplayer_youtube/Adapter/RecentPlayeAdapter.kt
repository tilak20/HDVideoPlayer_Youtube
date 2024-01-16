package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.VideoPlayActivity
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.RecentplayItemBinding
import java.io.File

class RecentPlayeAdapter(
    var mainActivity: Activity,
    var recentList: ArrayList<Video>,
    var from: String,
) : RecyclerView.Adapter<RecentPlayeAdapter.ViewData>() {

    class ViewData(var binding: RecentplayItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        RecentplayItemBinding.inflate(LayoutInflater.from(mainActivity))
    )

    override fun getItemCount() = recentList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {


            val file = File(recentList[position].videoPath)
            if (!file.exists()) {
                val rTempMap: MutableMap<String?, Video> = Application.getRecentMap().toMutableMap()
                rTempMap.remove(recentList[position].videoUri)
                "video not == ${recentList[position].videoPath}".log()
                Application.setRecentMap(rTempMap)
            }
            imageRecent.load(recentList[position].videoPath)

            root.setOnClickListener {
                val intent = Intent(mainActivity, VideoPlayActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("video_title", recentList[position].videoTitle)
                intent.putExtra("video_from", "RecentVideo")
                mainActivity.startActivity(intent)
            }
        }
    }

}