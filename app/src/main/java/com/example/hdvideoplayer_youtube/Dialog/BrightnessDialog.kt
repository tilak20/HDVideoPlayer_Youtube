package com.example.hdvideoplayer_youtube.Dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.hdvideoplayer_youtube.R

class BrightnessDialog : AppCompatDialogFragment() {

    private lateinit var bright_no: TextView
    private lateinit var cross: ImageView
    private lateinit var seekBar: SeekBar

    @SuppressLint("MissingInflatedId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.activity_brightness_dialog, null)
        builder.setView(view)
        cross = view.findViewById(R.id.brt_close)
        bright_no = view.findViewById(R.id.brt_number)
        seekBar = view.findViewById(R.id.brt_seekbar)
        val brightness = Settings.System.getInt(
            requireContext().contentResolver, Settings.System.SCREEN_BRIGHTNESS, 0
        )
        bright_no.text = brightness.toString() + ""
        seekBar.progress = brightness
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val context = context!!.applicationContext
                val canWrite = Settings.System.canWrite(context)
                if (canWrite) {
                    val sBrightness = progress * 255 / 255
                    bright_no.text = sBrightness.toString() + ""
                    Settings.System.putInt(
                        context.contentResolver,
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                    )
                    Settings.System.putInt(
                        context.contentResolver, Settings.System.SCREEN_BRIGHTNESS, sBrightness
                    )
                } else {
                    Toast.makeText(
                        context, "Enable write settings for brightness control", Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    intent.data = Uri.parse("package:" + context.packageName)
                    startActivityForResult(intent, 0)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        cross.setOnClickListener { dismiss() }
        return builder.create()
    }
}