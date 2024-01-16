package com.example.hdvideoplayer_youtube.Activity

import android.app.PictureInPictureParams
import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.format.Formatter
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.currentVideoList
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Activity.MainActivity.Companion.recentList
import com.example.hdvideoplayer_youtube.Activity.SearchVideoAct.Companion.SearchvideoList
import com.example.hdvideoplayer_youtube.Adapter.PlaybackIconsAdapter
import com.example.hdvideoplayer_youtube.Dialog.BrightnessDialog
import com.example.hdvideoplayer_youtube.Dialog.PlaylistDialog
import com.example.hdvideoplayer_youtube.Dialog.VolumeDialog
import com.example.hdvideoplayer_youtube.Model.OnSwipeTouchListener
import com.example.hdvideoplayer_youtube.ModelData.IconModel
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.databinding.ActivityVideoPlayBinding
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlin.math.abs
import kotlin.math.ceil

class VideoPlayActivity : BaseAct<ActivityVideoPlayBinding>() {

    lateinit var VideoName: String
    lateinit var statusUri: Uri
    lateinit var videoUri: Uri
    lateinit var videoFrom: String
    lateinit var player: SimpleExoPlayer
    var position = 0
    lateinit var videoTitle: String
    lateinit var title: TextView
    lateinit var controlsMode: ControlsMode

    enum class ControlsMode {
        LOCK, FULLSCREEN
    }

    lateinit var videoBack: ImageView
    lateinit var lock: ImageView
    lateinit var unlock: ImageView
    lateinit var scaling: ImageView
    lateinit var videoList: ImageView
    lateinit var videoMore: ImageView
    lateinit var root: RelativeLayout
    lateinit var nextButton: ImageView
    lateinit var previousButton: ImageView

    //horizontal recyclerview variables
    private val iconModelArrayList: ArrayList<IconModel> = ArrayList()
    lateinit var playbackIconsAdapter: PlaybackIconsAdapter
    lateinit var recyclerViewIcons: RecyclerView
    var expand = false
    lateinit var nightMode: View
    var dark = false
    var mute = false
    lateinit var parameters: PlaybackParameters
    var speed = 0f
    lateinit var pictureInPicture: PictureInPictureParams.Builder
    lateinit var eqContainer: FrameLayout

    //horizontal recyclerview variables
    //swipe and zoom variables
    private var device_height = 0 //swipe and zoom variables
    private var device_width = 0  //swipe and zoom variables
    private var brightness = 0  //swipe and zoom variables
    private var media_volume = 0
    var start = false
    var left = false
    var right: Boolean = false
    private var baseX = 0f
    private var baseY: Float = 0f
    var swipe_move = false
    private var diffX: Long = 0
    private var diffY: Long = 0
    val MINIMUM_DISTANCE = 100
    var success = false

    lateinit var audioManager: AudioManager
    var singleTap = false

    companion object {

        lateinit var zoomLayout: RelativeLayout
        private var scale_factor = 1.0f
        lateinit var zoom_perc: TextView
        lateinit var zoomContainer: RelativeLayout
        lateinit var vol_progress_container: LinearLayout
        lateinit var vol_text_container: LinearLayout
        lateinit var brt_progress_container: LinearLayout
        lateinit var brt_text_container: LinearLayout
    }

    lateinit var brt_icon: ImageView

    lateinit var vol_text: TextView
    lateinit var brt_text: TextView
    lateinit var total_duration: TextView
    lateinit var vol_progress: ProgressBar
    lateinit var brt_progress: ProgressBar
    lateinit var vol_icon: ImageView

    var currentList = ArrayList<Video>()

    lateinit var scaleGestureDetector: ScaleGestureDetector
    var double_tap = false
    lateinit var double_tap_playpause: RelativeLayout

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityVideoPlayBinding.inflate(layoutInflater)

    override fun initUI() {

        hideBottomBar()
        getIntentData()

        when (videoFrom) {
            "Folder" -> {
                currentList.addAll(currentVideoList)
                title.text = currentList[position].videoTitle
            }

            "RecentVideo" -> {
                currentList.addAll(recentList)
                title.text = recentList[position].videoTitle
            }

            "Search" -> {
                currentList.addAll(SearchvideoList)
                title.text = currentList[position].videoTitle
            }

            else -> {
                title.text = VideoName
            }
        }

        createPlayer()

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        device_width = displayMetrics.widthPixels
        device_height = displayMetrics.heightPixels

        initClick()
        horizontalIconList()
    }

    fun playError() {
        player.addListener(object : Player.EventListener {
            override fun onPlayerError(error: ExoPlaybackException) {
                ("Video Playing Error").tos()
            }
        })
        player.playWhenReady = true
    }

    fun createPlayer() {
        binding.apply {
            player = SimpleExoPlayer.Builder(this@VideoPlayActivity).build()
            exoplayerView.player = player

            val mediaItem = if (videoFrom == "Adapter") {
                MediaItem.fromUri(statusUri)
            } else {
                MediaItem.fromUri(currentList[position].videoUri)
            }

            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }

    fun hideBottomBar() {
        val v = window.decorView
        if (Build.VERSION.SDK_INT in 12..18) {
            v.systemUiVisibility = View.GONE
        } else {
            val uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            v.systemUiVisibility = uiOptions
        }
    }

    fun getIntentData() {
        position = intent.getIntExtra("position", 1)
        videoTitle = intent.getStringExtra("video_title").toString()

        if (intent.getStringExtra("video_from") != null) {
            videoFrom = intent.getStringExtra("video_from").toString()
        }
        if (intent.getStringExtra("status") != null) {
            statusUri = intent.getStringExtra("status")!!.toUri()
            "status uri == ${statusUri}".log()
        }
        if (intent.getStringExtra("VideoName") != null) {
            VideoName = intent.getStringExtra("VideoName")!!.toString()
        }

        initViews()

        nextButton.isClickable = true
        nextButton.isEnabled = true
    }

    fun horizontalIconList() {
        iconModelArrayList.add(IconModel(R.drawable.ic_right, ""))
        iconModelArrayList.add(IconModel(R.drawable.ic_night_mode, "Night"))
        iconModelArrayList.add(IconModel(R.drawable.ic_pip_mode, "Popup"))
        iconModelArrayList.add(IconModel(R.drawable.ic_equalizer, "Equalizer"))
        iconModelArrayList.add(IconModel(R.drawable.ic_rotate, "Rotate"))
        playbackIconsAdapter = PlaybackIconsAdapter(iconModelArrayList, this) { position ->

            if (position == 0) {
                if (expand) {
                    iconModelArrayList.clear()
                    iconModelArrayList.add(IconModel(R.drawable.ic_right, ""))
                    iconModelArrayList.add(IconModel(R.drawable.ic_night_mode, "Night"))
                    iconModelArrayList.add(IconModel(R.drawable.ic_pip_mode, "Popup"))
                    iconModelArrayList.add(IconModel(R.drawable.ic_equalizer, "Equalizer"))
                    iconModelArrayList.add(IconModel(R.drawable.ic_rotate, "Rotate"))
                    playbackIconsAdapter.notifyDataSetChanged()
                    expand = false
                } else {
                    if (iconModelArrayList.size == 5) {
                        iconModelArrayList.add(IconModel(R.drawable.ic_volume_off, "Mute"))
                        iconModelArrayList.add(IconModel(R.drawable.ic_volume, "Volume"))
                        iconModelArrayList.add(
                            IconModel(
                                R.drawable.ic_brightness, "Brightness"
                            )
                        )
                        iconModelArrayList.add(IconModel(R.drawable.ic_speed, "Speed"))
                        iconModelArrayList.add(IconModel(R.drawable.ic_subtitle, "Subtitle"))
                    }
                    iconModelArrayList[position] = IconModel(R.drawable.ic_left, "")
                    playbackIconsAdapter.notifyDataSetChanged()
                    expand = true
                }
            }
            if (position == 1) {
                //night mode
                if (dark) {
                    nightMode.visibility = View.GONE
                    iconModelArrayList[position] = IconModel(R.drawable.ic_night_mode, "Night")
                    playbackIconsAdapter.notifyDataSetChanged()
                    dark = false
                } else {
                    nightMode.visibility = View.VISIBLE
                    iconModelArrayList[position] = IconModel(R.drawable.ic_night_mode, "Day")
                    playbackIconsAdapter.notifyDataSetChanged()
                    dark = true
                }
            }
            if (position == 2) {
                //popup
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val aspectRatio = Rational(16, 9)
                    pictureInPicture.setAspectRatio(aspectRatio)
                    enterPictureInPictureMode(pictureInPicture.build())
                } else {
                    Log.wtf("not oreo", "yes")
                }
            }
            if (position == 3) {
                ("Equilizer Pending").log()
            }
            if (position == 4) {
                //rotate
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    playbackIconsAdapter.notifyDataSetChanged()
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    playbackIconsAdapter.notifyDataSetChanged()
                }
            }
            if (position == 5) {
                //mute
                if (mute) {
                    player.volume = 100f
                    iconModelArrayList[position] = IconModel(R.drawable.ic_volume_off, "Mute")
                    playbackIconsAdapter.notifyDataSetChanged()
                    mute = false
                } else {
                    player.volume = 0f
                    iconModelArrayList[position] = IconModel(R.drawable.ic_volume, "unMute")
                    playbackIconsAdapter.notifyDataSetChanged()
                    mute = true
                }
            }
            if (position == 6) {
                //volume
                val volumeDialog = VolumeDialog()
                volumeDialog.show(supportFragmentManager, "dialog")
                playbackIconsAdapter.notifyDataSetChanged()
            }
            if (position == 7) {
                //brightness
                val brightnessDialog = BrightnessDialog()
                brightnessDialog.show(supportFragmentManager, "dialog")
                playbackIconsAdapter.notifyDataSetChanged()
            }
            if (position == 8) {
                //speed
                val alertDialog = AlertDialog.Builder(this@VideoPlayActivity)
                alertDialog.setTitle("Select PLayback Speed").setPositiveButton("OK", null)
                val items = arrayOf("0.5x", "1x Normal Speed", "1.25x", "1.5x", "2x")
                val checkedItem = -1
                alertDialog.setSingleChoiceItems(
                    items, checkedItem
                ) { dialog, which ->
                    when (which) {
                        0 -> {
                            speed = 0.5f
                            parameters = PlaybackParameters(speed)
                            player.playbackParameters = parameters
                        }

                        1 -> {
                            speed = 1f
                            parameters = PlaybackParameters(speed)
                            player.playbackParameters = parameters
                        }

                        2 -> {
                            speed = 1.25f
                            parameters = PlaybackParameters(speed)
                            player.playbackParameters = parameters
                        }

                        3 -> {
                            speed = 1.5f
                            parameters = PlaybackParameters(speed)
                            player.playbackParameters = parameters
                        }

                        4 -> {
                            speed = 2f
                            parameters = PlaybackParameters(speed)
                            player.playbackParameters = parameters
                        }

                        else -> {}
                    }
                }
                val alert = alertDialog.create()
                alert.show()
            }
            if (position == 9) {
                //subtitle
                ("Subtitle Pending").log()
            }
        }
        val layoutManager = LinearLayoutManager(
            this, RecyclerView.HORIZONTAL, true
        )
        recyclerViewIcons.layoutManager = layoutManager
        recyclerViewIcons.adapter = playbackIconsAdapter
        playbackIconsAdapter.notifyDataSetChanged()

    }

    fun initClick() {

        lock.setOnClickListener {
            controlsMode = ControlsMode.FULLSCREEN
            root.visibility = View.VISIBLE
            lock.visibility = View.INVISIBLE
            "unLocked".tos()
        }

        unlock.setOnClickListener {
            controlsMode = ControlsMode.LOCK
            root.visibility = View.INVISIBLE
            lock.visibility = View.VISIBLE
            "Locked".tos()
        }

        binding.apply {
            videoBack.setOnClickListener {
                player.release()
                finish()
            }
            videoList.setOnClickListener {
                val playlistDialog = PlaylistDialog(currentList)
                playlistDialog.show(supportFragmentManager, playlistDialog.tag)
            }
            previousButton.setOnClickListener {
                try {
                    player.stop()
                    position--
                    "play == 1".log()
                    createPlayer()
                    title.text = currentList[position].videoTitle
                } catch (e: Exception) {
                    "no Previous Video".tos()

                    player.release()
                    "Previous Error == ${e}".log()
                    finish()
                }
            }
            nextButton.setOnClickListener {
                try {
                    player.stop()
                    position++
                    "play == 1".log()
                    createPlayer()
                    title.text = currentList[position].videoTitle
                } catch (e: java.lang.Exception) {
                    "no Next Video".tos()
                    player.release()
                    "Next Error == ${e.message}".log()
                    finish()
                }
            }
            scaling.setOnClickListener {
                changeAspectRatio()
            }
            videoMore.setOnClickListener {
                val popupMenu = PopupMenu(this@VideoPlayActivity, videoMore)
                popupMenu.inflate(R.menu.player_menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.next -> nextButton.performClick()
                        R.id.send -> {
                            val uri = Uri.parse(currentList[position].videoPath)
                            val shareIntent = Intent(Intent.ACTION_SEND)
                            shareIntent.type = "video/*"
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                            startActivity(Intent.createChooser(shareIntent, "Share Video via"))
                        }

                        R.id.properties -> {
                            val milliSeconds: Double =
                                currentList[position].videoDuration.toDouble()
                            val alertDialog =
                                android.app.AlertDialog.Builder(this@VideoPlayActivity)
                            alertDialog.setTitle("Properties")
                            val one = "File: " + currentList[position].videoTitle
                            val path: String = currentList[position].videoPath
                            val indexOfPath = path.lastIndexOf("/")
                            val two = "Path: " + path.substring(0, indexOfPath)
                            val three = "Size: " + Formatter.formatFileSize(
                                this@VideoPlayActivity, currentList[position].videoSize
                            )
                            val four = "Length: " + timeConversion(milliSeconds.toLong())
                            val namewithFormat: String = currentList[position].videoTitle
                            val index = namewithFormat.lastIndexOf(".")
                            val format = currentList[position].videoMime
                            val five = "Format: $format"

                            alertDialog.setMessage(
                                """
                                    $one
                                    
                                    $two
                                    
                                    $three
                                    
                                    $four
                                    
                                    $five                                    
                                    """.trimIndent()
                            )
                            alertDialog.setPositiveButton(
                                "OK"
                            ) { dialog, which -> dialog.dismiss() }
                            alertDialog.show()
                        }

                        R.id.delete -> {
                            val alertDialogDelete =
                                android.app.AlertDialog.Builder(this@VideoPlayActivity)
                            alertDialogDelete.setTitle("Delete")
                            alertDialogDelete.setMessage("Do you want to delete this video")
                            alertDialogDelete.setPositiveButton(
                                "Delete"
                            ) { dialog, which ->

                                videoUri = currentList[position].videoUri.toUri()



                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                                    contentResolver.delete(videoUri, null, null)
                                    currentList.removeAt(position)
                                    val rTempMap: MutableMap<String?, Video> =
                                        Application.getRecentMap().toMutableMap()
                                    rTempMap.remove(currentList[position].videoUri)
                                    Application.setRecentMap(rTempMap)

                                    val videoCollection: Collection<Video> =
                                        Application.getRecentMap().values
                                    currentList = java.util.ArrayList(videoCollection)


                                    if (videoFrom == "Folder") {
                                        currentVideoList.addAll(currentList)
                                    } else {
                                        recentList.addAll(currentList)
                                    }
                                    finish()
                                    ("Video Deleted").tos()
                                } else {
                                    try {
                                        val rTempMap: MutableMap<String?, Video> =
                                            Application.getRecentMap().toMutableMap()
                                        rTempMap.remove(currentList[position].videoUri)
                                        Application.setRecentMap(rTempMap)

                                        val videoCollection: Collection<Video> =
                                            Application.getRecentMap().values
                                        currentList = java.util.ArrayList(videoCollection)


                                        if (videoFrom == "Folder") {
                                            currentVideoList.addAll(currentList)
                                        } else {
                                            recentList.addAll(currentList)
                                        }
                                        deleteImageAPI29(videoUri)
                                    } catch (e: Exception) {
                                        "error = ${e.message}".log()
                                    }
                                }

                            }
                            alertDialogDelete.setNegativeButton(
                                "Cancel"
                            ) { dialog, _ -> dialog.dismiss() }
                            alertDialogDelete.show()
                        }

                        R.id.subtitle -> {
                            "Subtitle".log()
                        }
                    }
                    true
                }
                popupMenu.show()
            }
            exoplayerView.setOnTouchListener(object : OnSwipeTouchListener(this@VideoPlayActivity) {
                override fun onTouch(view: View?, motionEvent: MotionEvent): Boolean {
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            exoplayerView.showController()
                            start = true
                            if (motionEvent.x < device_width / 2) {
                                left = true
                                right = false
                            } else if (motionEvent.x > device_width / 2) {
                                left = false
                                right = true
                            }
                            baseX = motionEvent.x
                            baseY = motionEvent.y
                        }

                        MotionEvent.ACTION_MOVE -> {
                            swipe_move = true
                            diffX = ceil((motionEvent.x - baseX).toDouble()).toLong()
                            diffY = ceil((motionEvent.y - baseY).toDouble()).toLong()
                            val brightnessSpeed = 0.01
                            if (abs(diffY) > MINIMUM_DISTANCE) {
                                start = true
                                if (abs(diffY) > abs(diffX)) {
                                    val value: Boolean
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        value = Settings.System.canWrite(applicationContext)
                                        if (value) {
                                            if (left) {
                                                try {
                                                    Settings.System.putInt(
                                                        contentResolver,
                                                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                                                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                                                    )
                                                    brightness = Settings.System.getInt(
                                                        contentResolver,
                                                        Settings.System.SCREEN_BRIGHTNESS
                                                    )
                                                } catch (e: Settings.SettingNotFoundException) {
                                                    e.printStackTrace()
                                                }
                                                var new_brightness: Int =
                                                    (brightness - diffY * brightnessSpeed).toInt()
                                                if (new_brightness > 250) {
                                                    new_brightness = 250
                                                } else if (new_brightness < 1) {
                                                    new_brightness = 1
                                                }
                                                val brt_percentage =
                                                    ceil(new_brightness.toDouble() / 250.0 * 100.0)
                                                brt_progress_container.visibility = View.VISIBLE
                                                brt_text_container.visibility = View.VISIBLE
                                                brt_progress.progress = brt_percentage.toInt()
                                                if (brt_percentage < 30) {
                                                    brt_icon.setImageResource(R.drawable.ic_brightness_low)
                                                } else if (brt_percentage > 30 && brt_percentage < 80) {
                                                    brt_icon.setImageResource(R.drawable.ic_brightness_moderate)
                                                } else if (brt_percentage > 80) {
                                                    brt_icon.setImageResource(R.drawable.ic_brightness)
                                                }
                                                brt_text.text = " " + brt_percentage.toInt() + "%"
                                                Settings.System.putInt(
                                                    contentResolver,
                                                    Settings.System.SCREEN_BRIGHTNESS,
                                                    new_brightness
                                                )
                                                val layoutParams =
                                                    this@VideoPlayActivity.window.attributes
                                                layoutParams.screenBrightness = brightness / 255f
                                                window.attributes = layoutParams
                                            } else if (right) {
                                                vol_text_container.visibility = View.VISIBLE
                                                media_volume =
                                                    audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                                                val maxVol: Int =
                                                    audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                                                val cal: Double =
                                                    diffY.toDouble() * (maxVol.toDouble() / ((device_height * 2).toDouble() - brightnessSpeed))
                                                var newMediaVolume: Int = media_volume - cal.toInt()
                                                if (newMediaVolume > maxVol) {
                                                    newMediaVolume = maxVol
                                                } else if (newMediaVolume < 1) {
                                                    newMediaVolume = 0
                                                }
                                                audioManager.setStreamVolume(
                                                    AudioManager.STREAM_MUSIC,
                                                    newMediaVolume,
                                                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
                                                )
                                                val volPer =
                                                    ceil(newMediaVolume.toDouble() / maxVol.toDouble() * 100.0)
                                                vol_text.text = " " + volPer.toInt() + "%"
                                                if (volPer < 1) {
                                                    vol_icon.setImageResource(R.drawable.ic_volume_off)
                                                    vol_text.visibility = View.VISIBLE
                                                    vol_text.text = "Off"
                                                } else if (volPer >= 1) {
                                                    vol_icon.setImageResource(R.drawable.ic_volume)
                                                    vol_text.visibility = View.VISIBLE
                                                }
                                                vol_progress_container.visibility = View.VISIBLE
                                                vol_progress.progress = volPer.toInt()
                                            }
                                            success = true
                                        } else {
                                            "Allow write settings for swipe controls".tos()
                                            val intent =
                                                Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                            intent.data = Uri.parse("package:$packageName")
                                            startActivityForResult(intent, 111)

                                        }
                                    }
                                }
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            swipe_move = false
                            start = false
                            vol_progress_container.visibility = View.GONE
                            brt_progress_container.visibility = View.GONE
                            vol_text_container.visibility = View.GONE
                            brt_text_container.visibility = View.GONE
                        }
                    }
                    scaleGestureDetector.onTouchEvent(motionEvent)
                    return super.onTouch(view, motionEvent)
                }

                override fun onDoubleTouch() {
                    super.onDoubleTouch()
                    if (double_tap) {
                        player.playWhenReady = true
                        double_tap_playpause.visibility = View.GONE
                        double_tap = false
                    } else {
                        player.playWhenReady = false
                        double_tap_playpause.visibility = View.VISIBLE
                        double_tap = true
                    }
                }

                override fun onSingleTouch() {
                    super.onSingleTouch()
                    singleTap = if (singleTap) {
                        binding.exoplayerView.showController()
                        false
                    } else {
                        binding.exoplayerView.hideController()
                        true
                    }
                    if (double_tap_playpause.visibility == View.VISIBLE) {
                        double_tap_playpause.visibility = View.GONE
                    }
                }
            })
        }
    }

    fun deleteImageAPI29(uri: Uri?) {
        val resolver = contentResolver
        try {
            resolver.delete(uri!!, null, null)
        } catch (securityException: SecurityException) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val recoverableSecurityException = securityException as RecoverableSecurityException
                val senderRequest = IntentSenderRequest.Builder(
                    recoverableSecurityException.userAction.actionIntent.intentSender
                ).build()
                deleteResultLauncher.launch(senderRequest)
            }
        }
    }

    var deleteResultLauncher: ActivityResultLauncher<IntentSenderRequest> =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result != null) {
                if (result.resultCode == RESULT_OK) {
                    val deleted = deleteMediaFile(videoUri, contentResolver)
                    if (deleted) {
                        ("Video deleted.").tos()
                        finish()
                    } else {
                        ("Video deleted Not.").tos()
                    }
                }
            }
        }

    fun deleteMediaFile(contentUri: Uri, contentResolver: ContentResolver): Boolean {
        return try {
            val deletedRows = contentResolver.delete(contentUri, null, null)
            deletedRows > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun changeAspectRatio() {
        val aspectRatioFrameLayout =
            binding.exoplayerView.videoSurfaceView!!.parent as AspectRatioFrameLayout
        when (aspectRatioFrameLayout.resizeMode) {
            AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT -> {
                aspectRatioFrameLayout.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                "Zoom".tos()
            }

            AspectRatioFrameLayout.RESIZE_MODE_ZOOM -> {
                aspectRatioFrameLayout.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                "Zoom".tos()
            }

            AspectRatioFrameLayout.RESIZE_MODE_FILL -> {
                aspectRatioFrameLayout.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                "Fit".tos()
            }

            AspectRatioFrameLayout.RESIZE_MODE_FIT -> {
                aspectRatioFrameLayout.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
                "Width".tos()
            }

            AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH -> {
                aspectRatioFrameLayout.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
                "Height".tos()
            }
        }
    }

    fun initViews() {


        nextButton = findViewById(R.id.exonext)
        previousButton = findViewById(R.id.exo_prev)
        title = findViewById(R.id.video_title)
        videoBack = findViewById(R.id.video_back)
        lock = findViewById(R.id.lock)
        unlock = findViewById(R.id.unlock)
        scaling = findViewById(R.id.scaling)
        root = findViewById(R.id.root_layout)
        nightMode = findViewById(R.id.night_mode)
        videoList = findViewById(R.id.video_list)
        videoMore = findViewById(R.id.video_more)
        recyclerViewIcons = findViewById(R.id.recyclerview_icon)
        eqContainer = findViewById(R.id.eqFrame)
        zoomLayout = findViewById(R.id.zoom_layout)
        zoom_perc = findViewById(R.id.zoom_percentage)
        zoomContainer = findViewById(R.id.zoom_container)
        double_tap_playpause = findViewById(R.id.double_tap_play_pause)
        vol_icon = findViewById(R.id.vol_icon)

        vol_text = findViewById(R.id.vol_text)
        brt_text = findViewById(R.id.brt_text)
        vol_progress = findViewById(R.id.vol_progress)
        brt_progress = findViewById(R.id.brt_progress)
        vol_progress_container = findViewById(R.id.vol_progress_container)
        brt_progress_container = findViewById(R.id.brt_progress_container)
        vol_text_container = findViewById(R.id.vol_text_container)
        brt_text_container = findViewById(R.id.brt_text_container)
        brt_icon = findViewById(R.id.brt_icon)

        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        scaleGestureDetector = ScaleGestureDetector(
            this, ScaleDetector()
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPicture = PictureInPictureParams.Builder()
        }
    }

    class ScaleDetector : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale_factor *= detector.scaleFactor
            scale_factor = 0.5f.coerceAtLeast(scale_factor.coerceAtMost(6.0f))
            zoomLayout.scaleX = scale_factor
            zoomLayout.scaleY = scale_factor
            val percentage: Int = (scale_factor * 100).toInt()
            zoom_perc.text = " $percentage%"
            zoomContainer.visibility = View.VISIBLE
            brt_text_container.visibility = View.GONE
            vol_text_container.visibility = View.GONE
            brt_progress_container.visibility = View.GONE
            vol_progress_container.visibility = View.GONE
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            zoomContainer.visibility = View.GONE
            super.onScaleEnd(detector)
        }
    }

    fun timeConversion(millie: Long?): String? {
        return if (millie != null) {
            val seconds = millie / 1000
            val sec = seconds % 60
            val min = seconds / 60 % 60
            val hrs = seconds / (60 * 60) % 24
            if (hrs > 0) {
                String.format("%02d:%02d:%02d", hrs, min, sec)
            } else {
                String.format("%02d:%02d", min, sec)
            }
        } else {
            null
        }
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

}