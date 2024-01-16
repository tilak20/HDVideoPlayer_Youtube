package com.example.hdvideoplayer_youtube.Dialog

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.DeleteDialogBinding
import com.google.ads.sdk.AdsManager

class DeleteDialog constructor(activity: Activity, onClick: (String) -> Unit) {
    var dialog: Dialog = Dialog(activity)
    var binding: DeleteDialogBinding = DeleteDialogBinding.inflate(LayoutInflater.from(activity))

    init {
        dialog.setContentView(binding.root)
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnYes.setOnClickListener {
            onClick.invoke("Yes")
            dialog.dismiss()
        }
        binding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}