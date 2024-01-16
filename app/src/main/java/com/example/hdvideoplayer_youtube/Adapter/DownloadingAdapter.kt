package com.example.hdvideoplayer_youtube.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hashtagapi.Utils.getFileSize
import com.example.hdvideoplayer_youtube.ModelData.Downloading
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.PendingdownloadBinding


class DownloadingAdapter(
    val requireActivity: Activity,
    var list: ArrayList<Downloading>
) : RecyclerView.Adapter<DownloadingAdapter.ViewData>() {

    class ViewData(val binding: PendingdownloadBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        PendingdownloadBinding.inflate(LayoutInflater.from(requireActivity))
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        val item = list[position]
        holder.binding.apply {
            progressbar.visibility =
                if (item.progress.toInt() == 100) View.INVISIBLE else View.VISIBLE
            tkaprogress.visibility =
                if (item.progress.toInt() == 100) View.INVISIBLE else View.VISIBLE

            progressbar.progress = item.progress.toInt()

            txtCurrentsize.text = item.currentSize

            tkaprogress.text = "${item.progress}%"
            materialTextView2.text = item.name
            txtquality.text = item.quality
            imageFilterView2.load(item.src)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(it: ArrayList<Downloading>) {
        list = it
        notifyDataSetChanged()
    }

}