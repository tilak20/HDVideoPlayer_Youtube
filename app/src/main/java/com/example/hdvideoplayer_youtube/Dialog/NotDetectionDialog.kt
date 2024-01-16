package com.example.hdvideoplayer_youtube.Dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.VideonotfoundDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("SetTextI18n")
class NotDetectionDialog(var activity: Activity) {
    var dialog: BottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetdialogTheme)
    var binding: VideonotfoundDialogBinding =
        VideonotfoundDialogBinding.inflate(LayoutInflater.from(activity))

    init {
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            .setBackgroundResource(
                android.R.color.transparent
            )

        binding.apply {
            tvdetect.text = "Video not detected?"
            oone.text =
                "Option 1 : Play the video first"

            ok.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}