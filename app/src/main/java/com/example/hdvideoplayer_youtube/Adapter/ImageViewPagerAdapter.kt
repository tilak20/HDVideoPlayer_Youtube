package com.example.hdvideoplayer_youtube.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.ImageShowAct
import com.example.hdvideoplayer_youtube.ModelData.CurrentImageMd
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.ViewpagerItemBinding

class ImageViewPagerAdapter(
    var activity: ImageShowAct, var currentImageList: ArrayList<CurrentImageMd>
) : RecyclerView.Adapter<ImageViewPagerAdapter.ViewData>() {
    class ViewData(var binding: ViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        ViewpagerItemBinding.inflate(LayoutInflater.from(activity), parent, false)
    )

    override fun getItemCount() = currentImageList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {

            try {

//                ivSlide2.setImageURI(currentImageList[position].imageUri.toUri())
                ivSlide2.load(currentImageList[position].imagePath)

            } catch (e: Error) {
                "error == ${e.message}".log()
            }

        }
    }
}