package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.ModelData.FolderMD
import com.example.hdvideoplayer_youtube.databinding.FolderitemlistBinding

class FolderAdapter(
    var activity: Activity, var folderList: ArrayList<FolderMD>, var onClick: (Int, String) -> Unit
) : RecyclerView.Adapter<FolderAdapter.ViewData>() {
    class ViewData(var binding: FolderitemlistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewData(
        FolderitemlistBinding.inflate(LayoutInflater.from(activity))
    )

    override fun getItemCount() = folderList.size

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.binding.apply {

//            ("Adapter == ${folderList.size}").log()


            root.setOnClickListener {
                ("Bucket == ${folderList[position].videoBucket}").log()
                onClick(position, folderList[position].videoBucket)
            }

            videoFileName.text = folderList[position].videoBucket

        }
    }
}