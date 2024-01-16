package com.example.hdvideoplayer_youtube.Dialog

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.example.hashtagapi.Utils.listofVideo
import com.example.hashtagapi.Utils.mulaVideoList
import com.example.hdvideoplayer_youtube.Activity.RecentAddedAct
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.load
import com.example.hdvideoplayer_youtube.databinding.ActivityVideoLatestDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File

class VideoLatestDialog(
    var activity: Activity,
    var files: ArrayList<File>,
    var from: String,
) {
    var dialog: BottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetdialogTheme)

    var binding: ActivityVideoLatestDialogBinding =
        ActivityVideoLatestDialogBinding.inflate(LayoutInflater.from(activity))

    init {
        dialog.setContentView(binding.root)

        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            .setBackgroundResource(
                android.R.color.transparent
            )

        binding.apply {
            binding.imageVideo.load(files[0].absolutePath)

            txtlinkSingle.text = files[0].name

        }

        binding.root.setOnClickListener {

            listofVideo.clear()
            mulaVideoList.postValue(listofVideo)

            if (from == "Browser") {
                activity.startActivity(Intent(activity, RecentAddedAct::class.java))
            } else {
                activity.startActivity(Intent(activity, RecentAddedAct::class.java))
                activity.finish()
            }

            dialog.dismiss()
        }

        dialog.setOnCancelListener {
            listofVideo.clear()
            mulaVideoList.postValue(listofVideo)
        }

        dialog.show()
    }
}