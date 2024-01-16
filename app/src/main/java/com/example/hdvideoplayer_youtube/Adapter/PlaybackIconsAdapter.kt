package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hdvideoplayer_youtube.Activity.VideoPlayActivity
import com.example.hdvideoplayer_youtube.ModelData.IconModel
import com.example.hdvideoplayer_youtube.databinding.IconsLayoutBinding

class PlaybackIconsAdapter(
    var iconModelArrayList: ArrayList<IconModel>,
    var videoPlayActivity: Activity,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<PlaybackIconsAdapter.ViewData>() {

    class ViewData(var binding: IconsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        IconsLayoutBinding.inflate(LayoutInflater.from(videoPlayActivity))
    )

    override fun getItemCount() = iconModelArrayList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {
            playbackIcon.setImageResource(iconModelArrayList[position].imageView)
            iconTitle.text = iconModelArrayList[position].iconTitle

            root.setOnClickListener {

                onClick.invoke(position)

            }

        }
    }


}