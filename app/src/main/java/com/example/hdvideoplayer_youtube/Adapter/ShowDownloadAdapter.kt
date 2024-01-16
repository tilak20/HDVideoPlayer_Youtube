package com.downloadersocial.fastvideodownloader.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hdvideoplayer_youtube.Activity.VideoPlayActivity
import com.example.hdvideoplayer_youtube.ModelData.UrlModel
import com.example.hdvideoplayer_youtube.Utils.loadImg
import com.example.hdvideoplayer_youtube.databinding.ShowdownloadItemBinding
import java.io.File
import java.util.*

class ShowDownloadAdapter(
    val activity: Activity, var list1: ArrayList<UrlModel>, var onClick: (Int) -> Unit
) : RecyclerView.Adapter<ShowDownloadAdapter.ShowDownloadVH>() {

    class ShowDownloadVH(val binding: ShowdownloadItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShowDownloadVH(
        ShowdownloadItemBinding.inflate(LayoutInflater.from(activity))
    )

    override fun getItemCount() = list1.size

    override fun onBindViewHolder(holder: ShowDownloadVH, position: Int) {
        holder.binding.apply {
            val item = list1[position]

            imgDownload.loadImg(item.url)

            txtSize.text = item.formattedSize

            txtTime.text = item.duration

            txtTitle.isSelected = true
            txtTitle.text = item.url.name

            btnDelete.setOnClickListener {
                onClick(position)
            }
            btnShare.setOnClickListener {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "video/*"
                share.putExtra(
                    Intent.EXTRA_STREAM, FileProvider.getUriForFile(
                        activity, activity.packageName + ".provider", File(item.url.path)
                    )
                )
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                activity.startActivity(Intent.createChooser(share, "Video"))
            }

            root.setOnClickListener {
//                activity.startActivity(
//                    Intent(activity, VideoPlayActivity::class.java).putExtra(
//                        "videoPath", item.url.path
//                    ).putExtra("videoTitle", item.url.name).putExtra("video_from", "Adapter")
//                        .putExtra("position", position)
//
//                )


                activity.startActivity(
                    Intent(activity, VideoPlayActivity::class.java).putExtra(
                        "status", item.url.toString()
                    ).putExtra("video_from", "Adapter").putExtra("position", position)
                        .putExtra("VideoName", "")
                )
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceList(list: ArrayList<UrlModel>) {
        list1 = list
        notifyDataSetChanged()
    }
}