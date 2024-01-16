package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.WhatsAppActivity
import com.example.hdvideoplayer_youtube.ModelData.MyStatus
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.WhatsappItemBinding

class StatusAdapter(
    var activity: Activity,
    var myStatusList: ArrayList<MyStatus>,
    var onClick: (Int, View, String, MyStatus) -> Unit
) : RecyclerView.Adapter<StatusAdapter.ViewData>() {
    class ViewData(var binding: WhatsappItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        WhatsappItemBinding.inflate(LayoutInflater.from(activity))
    )

    override fun getItemCount() = myStatusList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {

            if (myStatusList[position].path.endsWith(".mp4")) videoIcon.visibility =
                View.VISIBLE else videoIcon.visibility = View.GONE

            shapeableImageView.load(myStatusList[position].uri)
            shapeableImageView.setOnClickListener {
                onClick.invoke(position, videoIcon, "Play", myStatusList[position])
            }
            videoDownload.setOnClickListener {
                onClick.invoke(position, videoDownload, "Download", myStatusList[position])
            }
        }
    }
}