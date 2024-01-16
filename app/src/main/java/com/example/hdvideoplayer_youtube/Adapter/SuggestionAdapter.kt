package com.example.hdvideoplayer_youtube.Adapter

import android.app.Activity
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hdvideoplayer_youtube.databinding.SuggestionItemBinding

class SuggestionAdapter(
    val activity: Activity,
    val list: List<List<Any>>,
    val onClick :(Int)->Unit
) : RecyclerView.Adapter<SuggestionAdapter.SuggestionVH>() {

    class SuggestionVH(val binding: SuggestionItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SuggestionVH(
        SuggestionItemBinding.inflate(LayoutInflater.from(activity))
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SuggestionVH, position: Int) {
        holder.binding.apply {

            val item =list[position]
            val dataitem =item[0].toString()
            txtsuggestion.text = Html.fromHtml(dataitem)

            txtsuggestion.setOnClickListener {
                onClick(position)
            }
        }
    }
}