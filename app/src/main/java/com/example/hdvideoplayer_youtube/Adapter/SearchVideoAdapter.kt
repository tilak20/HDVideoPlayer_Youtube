package com.example.hdvideoplayer_youtube.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.FoldervideoitemBinding
import com.example.hdvideoplayer_youtube.databinding.VideoitemlistBinding

class SearchVideoAdapter(
    val mainActivity: Activity, var videoList: List<Video>, var onClick: (Int, String, View) -> Unit
) : RecyclerView.Adapter<SearchVideoAdapter.ViewData>() {

    class ViewData(var binding: FoldervideoitemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        FoldervideoitemBinding.inflate(LayoutInflater.from(mainActivity))
    )

    override fun getItemCount() = videoList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.binding.apply {
            shapeableImageView.load(videoList[position].thumb.toUri())

            videoFileName.text = videoList[position].videoName
            txtModiFied.text = videoList[position].videoDateadded

            txtDuration.text = Application.getVideoDuration(videoList[position].videoDuration)

            root.setOnClickListener {
                onClick(position, "root", root)
            }

            imgMore.gon()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun SearchData(searchList: ArrayList<Video>) {
        videoList = searchList
        notifyDataSetChanged()
    }
}