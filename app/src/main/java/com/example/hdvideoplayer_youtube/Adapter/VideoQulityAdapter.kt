package com.example.hdvideoplayer_youtube.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hashtagapi.Utils.getFileSize
import com.example.hdvideoplayer_youtube.Dialog.DetectVideoDialog
import com.example.hdvideoplayer_youtube.ModelData.DataList
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.RvVideoitemBinding

class VideoQulityAdapter(
    var activity: Activity,
    var list: ArrayList<DataList>,
    var onClick: (DataList, Int) -> Unit,
    var quality: (String) -> Unit
) : RecyclerView.Adapter<VideoQulityAdapter.VH>() {
    class VH(var binding: RvVideoitemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(RvVideoitemBinding.inflate(LayoutInflater.from(activity)))

    override fun getItemCount() = list.size

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]


        holder.binding.apply {

            if (item != null) {
                qulity.text = item.quality
            }

            if (getFileSize(item.filesize) == "0") {

            } else {
                progrssbar.visibility = View.INVISIBLE
                firstsize.text = getFileSize(item.filesize)
            }
            selected.foreground = (if (item.isSelected) {

                if (item != null) {
                    if (item.quality != null) {
                        quality.invoke(item.quality)
                    } else {
                        quality.invoke("-")
                    }
                }

                activity.getDrawable(R.drawable.tv_line_yellow)
            } else {
                activity.getDrawable(R.drawable.tv_line_shape)
            })

            root.setOnClickListener {

                onClick.invoke(item, position)

            }
        }
    }
}