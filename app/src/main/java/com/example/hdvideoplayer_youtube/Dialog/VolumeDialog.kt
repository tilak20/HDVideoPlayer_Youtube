package com.example.hdvideoplayer_youtube.Dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.hdvideoplayer_youtube.R

class VolumeDialog : AppCompatDialogFragment() {
    private lateinit var cross: ImageView
    private lateinit var volume_no: TextView
    private lateinit var seekBar: SeekBar
    lateinit var audioManager: AudioManager

    @SuppressLint("MissingInflatedId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.activity_volume_dialog, null)
        builder.setView(view)
        requireActivity().volumeControlStream = AudioManager.STREAM_MUSIC
        cross = view.findViewById(R.id.vol_close)
        volume_no = view.findViewById(R.id.vol_number)
        seekBar = view.findViewById(R.id.vol_seekbar)
        audioManager = requireContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        seekBar.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        seekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                val mediavolume: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                val maxvol: Int = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                val volPer = Math.ceil(mediavolume.toDouble() / maxvol.toDouble() * 100.0)
                volume_no.text = "" + volPer
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        cross.setOnClickListener { dismiss() }
        return builder.create()
    }

}