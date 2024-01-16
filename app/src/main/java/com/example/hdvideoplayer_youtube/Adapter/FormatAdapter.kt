package com.example.hdvideoplayer_youtube.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.ModelData.VideoModel.AdaptiveFormatsItem
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.FormatsItemBinding
import java.text.DecimalFormat

class FormatAdapter(
    var activity: Activity,
    var list: ArrayList<AdaptiveFormatsItem>,
    var audioBuffer: Long,
    var onClick: (String, String, Int, String) -> Unit
) : RecyclerView.Adapter<FormatAdapter.FormatVH>() {

    private var selectedItemPosition = -1
    var url = ""

    class FormatVH(var binding: FormatsItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FormatVH(FormatsItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = list.size

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: FormatVH, position: Int) {
        holder.binding.apply {

            val item = list[position]
            if (item.mimeType!!.contains("opus")) {
//                "Audio Url: ${list.size}".log()
                url = item.url!!
            }

//            val videoBuffer = item.bitrate
//            val buffer = videoBuffer!! + audioBuffer
//            val second = item.approxDurationMs!!.toLong() / 1000
//            val fileSize = (buffer * second) / 8
            val fileSize = item.contentLength!!.toLong() + audioBuffer
            "$audioBuffer || ${item.contentLength} || $fileSize".log()
            val formattedFileSize = formatFileSize(fileSize)

            formattedFileSize.log()
            txtFileSize.text = formattedFileSize

            if (item.mimeType.contains("vp9")) {
                if (item.qualityLabel != "144p" || item.qualityLabel.endsWith("HDR")) {
                    txtFormat.text = item.qualityLabel
                } else {
                    root.visibility = View.GONE
                }
            } else {
                root.visibility = View.GONE
            }

            if (position == selectedItemPosition) {
                txtFormat.setTextColor(ContextCompat.getColor(activity, R.color.red))
                txtFileSize.setTextColor(ContextCompat.getColor(activity, R.color.size_selected))
                linear.setBackgroundResource(R.drawable.txt_format_selected)
            } else {
                txtFormat.setTextColor(ContextCompat.getColor(activity, R.color.black))
                txtFileSize.setTextColor(ContextCompat.getColor(activity, R.color.size_unselected))
                linear.setBackgroundResource(R.drawable.txt_format_bg)
            }

            linear.setOnClickListener {
                "format url : ${item.url}".log()
                val previousSelectedItem = selectedItemPosition
                selectedItemPosition = holder.adapterPosition
                notifyItemChanged(previousSelectedItem)
                notifyItemChanged(selectedItemPosition)

                onClick(item.url!!, url, position, item.qualityLabel.toString())
            }
        }
    }

    fun formatFileSize(sizeInBytes: Long): String {
        val df = DecimalFormat("#.##")
        return when {
            sizeInBytes < 1000 -> "$sizeInBytes B"
            sizeInBytes < 1000 * 1000 -> "${df.format(sizeInBytes.toDouble() / 1024)} KB"
            sizeInBytes < 1000 * 1000 * 1000 -> "${df.format(sizeInBytes.toDouble() / (1024 * 1024))} MB"
            else -> "${df.format(sizeInBytes.toDouble() / (1024 * 1024 * 1024))} GB"
        }
    }
}