package com.example.hdvideoplayer_youtube.Activity

import android.annotation.SuppressLint
import android.app.PictureInPictureParams
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.media.*
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.downloadersocial.fastvideodownloader.ApiClient.YoutubeClient.Companion.getRetrofit
import com.downloadersocial.fastvideodownloader.ApiClient.YoutubeClient.Companion.getVideoRetrofit
import com.downloadersocial.fastvideodownloader.ApiInterface.TrendingInterface
import com.example.hdvideoplayer_youtube.Activity.Application.Companion.log
import com.example.hdvideoplayer_youtube.Adapter.PlaybackIconsAdapter
import com.example.hdvideoplayer_youtube.Adapter.SuggestVideoAdapter
import com.example.hdvideoplayer_youtube.Dialog.DownloadDialog
import com.example.hdvideoplayer_youtube.Model.OnSwipeTouchListener
import com.example.hdvideoplayer_youtube.ModelData.IconModel
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.Client
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.ContentsItem
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.Context
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.Request
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.ResultsItem
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.SuggestVideo
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.SuggestVideoBody
import com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel.User
import com.example.hdvideoplayer_youtube.ModelData.VideoModel.AdaptiveFormatsItem
import com.example.hdvideoplayer_youtube.ModelData.VideoModel.FormatsItem
import com.example.hdvideoplayer_youtube.ModelData.VideoModel.VideoBodyModel
import com.example.hdvideoplayer_youtube.ModelData.VideoModel.VideoModel
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.loadImg
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.ActivityYoutubeVideoBinding
import com.google.ads.sdk.AdsManager
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.SimpleExoPlayer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import kotlin.math.abs
import kotlin.math.ceil

class YoutubeVideoActivity : BaseAct<ActivityYoutubeVideoBinding>() {

    private lateinit var wakeLock: PowerManager.WakeLock
    var cleanedTitle: String = ""
    var videoThumbnail: String = ""
    var exoplayerVideoUrl: String = ""
    var videoTitle: String = ""
    var videoTime: String = ""
    var newList: ArrayList<ResultsItem> = arrayListOf()
    var formatList: ArrayList<FormatsItem> = arrayListOf()
    var widthPercentage: Double = 0.70
    lateinit var statusUri: Uri
    lateinit var videoFrom: String
    lateinit var player: SimpleExoPlayer
    var position = 0
    var status: String = ""
    lateinit var title: TextView
    lateinit var controlsMode: ControlsMode

    enum class ControlsMode {
        LOCK, FULLSCREEN
    }

    lateinit var videoBack: ImageView
    lateinit var lock: ImageView
    lateinit var unlock: ImageView
    lateinit var scaling: ImageView
    lateinit var videoMore: ImageView
    lateinit var root: RelativeLayout
    val iconModelArrayList: ArrayList<IconModel> = ArrayList()
    lateinit var vol_icon: ImageView
    lateinit var brt_icon: ImageView

    lateinit var vol_text: TextView
    lateinit var brt_text: TextView
    lateinit var total_duration: TextView
    lateinit var vol_progress: ProgressBar
    lateinit var brt_progress: ProgressBar


    //horizontal recyclerview variables
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
    private lateinit var window: Window
    var singleTap = false

    companion object {
        private val WRITE_PERMISSIONS = 2

        lateinit var zoomLayout: RelativeLayout
        private var scale_factor = 1.0f
        lateinit var zoom_perc: TextView
        lateinit var zoomContainer: RelativeLayout

        lateinit var vol_progress_container: LinearLayout
        lateinit var vol_text_container: LinearLayout
        lateinit var brt_progress_container: LinearLayout
        lateinit var brt_text_container: LinearLayout
    }

    lateinit var scaleGestureDetector: ScaleGestureDetector
    var double_tap = false
    lateinit var double_tap_playpause: RelativeLayout

    lateinit var url: String
    var list1: ArrayList<AdaptiveFormatsItem> = arrayListOf()
    lateinit var videoId: String

    var list: ArrayList<AdaptiveFormatsItem> = arrayListOf()
    var suggestList: ArrayList<ResultsItem> = arrayListOf()
    val cdList: ArrayList<ContentsItem> = arrayListOf()
    var isVideoDownload: Boolean = false
    var isAudioDownload: Boolean = false
    var videoName: String = ""
    var audioName: String = ""
    var videoBuffer: Int = 0
    var audioBuffer: Int = 0
    var audioLength: Long = 0
    var buffer: Int = 0

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityYoutubeVideoBinding.inflate(layoutInflater)

    override fun initUI() {
        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)
        window = getWindow()

        // for hide navigation bar and status bar in activity
        if (Build.VERSION.SDK_INT in 12..18) {
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else {
            val decorView = window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }

        val powerManager = getSystemService(POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ON_AFTER_RELEASE,
            "VideoActivity:wakeLock"
        )
        wakeLock.acquire()

        videoId = intent.getStringExtra("videoId").toString()
        videoTime = intent.getStringExtra("videoTime").toString()
//        hideBottomBar()
        getIntentData()

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        device_width = displayMetrics.widthPixels
        device_height = displayMetrics.heightPixels

        widthPercentage = 0.70 // Adjust this percentage as needed
        val desiredHeight = (widthPercentage * device_width).toInt()
        val layoutParams = binding.exoplayerView.layoutParams
        layoutParams.height = desiredHeight
        binding.exoplayerView.layoutParams = layoutParams

        "width == $device_width | | height == $device_height".log()

        horizontalIconList()

        "videoId: $videoId".log()

        binding.progressBar.visible()
        apiCalling()
//        suggestVideoApi()
        initClick()
    }

    fun createPlayer(videoUrl: String) {
        binding.apply {
            player = SimpleExoPlayer.Builder(this@YoutubeVideoActivity).build()
            exoplayerView.player = player
            val mediaItem = MediaItem.fromUri(videoUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }


    fun getIntentData() {
        position = intent.getIntExtra("position", 1)

        if (intent.getStringExtra("video_from") != null) {
            videoFrom = intent.getStringExtra("video_from").toString()
        }
        if (intent.getStringExtra("status") != null) {
            statusUri = intent.getStringExtra("status")!!.toUri()
        }
        initViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun horizontalIconList() {
        iconModelArrayList.add(IconModel(R.drawable.ic_night_mode, "Night"))
        iconModelArrayList.add(IconModel(R.drawable.ic_volume_off, "Mute"))
        iconModelArrayList.add(IconModel(R.drawable.ic_speed, "Speed"))
        playbackIconsAdapter =
            PlaybackIconsAdapter(iconModelArrayList, this@YoutubeVideoActivity) { position ->
                if (position == 0) {
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
                if (position == 1) {
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
                if (position == 2) {
                    //speed
                    val alertDialog = AlertDialog.Builder(this@YoutubeVideoActivity)
                    alertDialog.setTitle("Select PLayback Speed").setPositiveButton("OK", null)
                    val items = arrayOf("0.5x", "1x Normal Speed", "1.25x", "1.5x", "2x")
                    val checkedItem = -1
                    alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
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
            }
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, true)
        recyclerViewIcons.layoutManager = layoutManager
        recyclerViewIcons.adapter = playbackIconsAdapter
        playbackIconsAdapter.notifyDataSetChanged()

    }

    private fun initClick() {

        binding.apply {
            imgDownload.setOnClickListener {
                list1.clear()
                list.forEach {
                    if (it.mimeType!!.contains("vp9")) {
                        if (!it.qualityLabel!!.contains("HDR")) {
                            if (it.qualityLabel != "144p") {
                                list1.add(it)
                            } else {
                                "is 144p".log()
                            }
                        } else {
                            "HDR".log()
                        }
                    }
                }

                list.forEach {
                    if (it.mimeType!!.contains("opus")) {
                        if (it.audioQuality == "AUDIO_QUALITY_MEDIUM") {
                            url = it.url!!
                            audioBuffer = it.bitrate!!
                            audioLength = it.contentLength!!.toLong()
                        } else {
                            url = it.url!!
                            audioBuffer = it.bitrate!!
                            audioLength = it.contentLength!!.toLong()
                        }
                    }
                }

                DownloadDialog(
                    this@YoutubeVideoActivity,
                    list1,
                    url,
                    audioBuffer,
                    videoThumbnail,
                    videoTitle,
                    videoTime,
                    audioLength,
                    cleanedTitle
                ) {}
            }
        }

        lock.setOnClickListener {
            controlsMode = ControlsMode.FULLSCREEN
            root.visibility = View.VISIBLE
            lock.visibility = View.INVISIBLE
            Toast.makeText(this@YoutubeVideoActivity, "unLocked", Toast.LENGTH_SHORT).show()
        }

        unlock.setOnClickListener {
            controlsMode = ControlsMode.LOCK
            root.visibility = View.INVISIBLE
            lock.visibility = View.VISIBLE
            Toast.makeText(this@YoutubeVideoActivity, "Locked", Toast.LENGTH_SHORT).show()
        }

        binding.apply {

            videoBack.setOnClickListener {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    widthPercentage = 0.70
                    val desiredHeight = (widthPercentage * device_width).toInt()
                    val layoutParams = binding.exoplayerView.layoutParams
                    layoutParams.height = desiredHeight
                    binding.exoplayerView.layoutParams = layoutParams
                } else {
                    if (status == "OK") {
                        player.release()
                    } else {
                        status.log()
                    }
                    finish()
                }

            }

            scaling.setOnClickListener {
//                changeAspectRatio()
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                    val decorView = getWindow().decorView
                    val uiOptions =
                        (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
                    decorView.systemUiVisibility = uiOptions

                    val layoutParams = binding.exoplayerView.layoutParams
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                    binding.exoplayerView.layoutParams = layoutParams

                    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

                    widthPercentage = 0.70 // Adjust this percentage as needed
                    val desiredHeight = (widthPercentage * device_width).toInt()
                    val layoutParams = binding.exoplayerView.layoutParams
                    layoutParams.height = desiredHeight
                    binding.exoplayerView.layoutParams = layoutParams
//                    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

                }
            }

            exoplayerView.setOnTouchListener(object :
                OnSwipeTouchListener(this@YoutubeVideoActivity) {
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
                                            val layoutParams = window.attributes
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
                                        Toast.makeText(
                                            applicationContext,
                                            "Allow write settings for swipe controls",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                        intent.data = Uri.parse("package:$packageName")
                                        startActivityForResult(intent, 111)
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

    fun initViews() {
        title = findViewById(R.id.video_title)
        videoBack = findViewById(R.id.video_back)
        lock = findViewById(R.id.lock)
        unlock = findViewById(R.id.unlock)
        scaling = findViewById(R.id.scaling)
        root = findViewById(R.id.root_layout)
        nightMode = findViewById(R.id.night_mode)
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

        scaleGestureDetector = ScaleGestureDetector(this, ScaleDetector())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPicture = PictureInPictureParams.Builder()
        }
    }

    class ScaleDetector : ScaleGestureDetector.SimpleOnScaleGestureListener() {
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

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ("Permission Granted...").log()
//                saveVideoToDownloads()
            } else {
                ("Permission Denied...").log()
            }
        }
    }

    fun suggestVideoApi() {

        val bodyModel = SuggestVideoBody(
            contentCheckOk = true, context = Context(
                request = Request(
                    internalExperimentFlags = emptyList(), useSsl = true
                ), client = Client(
                    hl = "en-GB",
                    gl = "IN",
                    clientName = "WEB",
                    originalUrl = "https://www.youtube.com",
                    clientVersion = "2.20230831.09.00",
                    platform = "DESKTOP"
                ), user = User(lockedSafetyMode = false)
            ), racyCheckOk = true, videoId = videoId
        )

        getRetrofit().create(TrendingInterface::class.java).suggestVideo(body = bodyModel)
            .enqueue(object : Callback<SuggestVideo> {
                override fun onResponse(
                    call: Call<SuggestVideo>, response: Response<SuggestVideo>
                ) {
                    suggestList.clear()
                    if (response.isSuccessful && !isDestroyed) {
                        binding.progressBar.gon()
                        suggestList.addAll(response.body()!!.contents!!.twoColumnWatchNextResults!!.secondaryResults!!.secondaryResults!!.results as ArrayList<ResultsItem>)
                        cdList.addAll(response.body()!!.contents?.twoColumnWatchNextResults?.results?.results?.contents as ArrayList<ContentsItem>)
                        // channel details
                        val cd =
                            response.body()!!.contents?.twoColumnWatchNextResults?.results?.results?.contents!![1]!!.videoSecondaryInfoRenderer!!.owner!!.videoOwnerRenderer!!

                        binding.channelDetails.visible()
                        binding.imageFilterView2.loadImg(cd.thumbnail?.thumbnails?.get(0)?.url!!)
                        binding.channelName.text = cd.title?.runs?.get(0)?.text
                        binding.subscribers.text = cd.subscriberCountText!!.simpleText

                        suggestList.forEach {
                            if (it.compactVideoRenderer != null) {

                                if (it.compactVideoRenderer.videoId != null) {
                                    if (it.compactVideoRenderer.badges?.get(0)?.metadataBadgeRenderer?.label != "LIVE") {
//                                        "Response: ${it.compactVideoRenderer.videoId}".log()
                                        newList.addAll(listOf(it))
                                    }
                                }
                            } else {
                                "Response: Data null ${it.compactVideoRenderer?.videoId}".log()
                            }
//                        "Response: ${response.body()!!.contents!!.twoColumnWatchNextResults}".log()

                        }

//                        "${suggestList[0].compactVideoRenderer!!.publishedTimeText!!.simpleText}".log()
//                        "${suggestList[0].compactVideoRenderer!!.videoId}".log()
//                        "${suggestList[0].compactVideoRenderer!!.thumbnail?.thumbnails?.get(0)}".log()
//
                        binding.suggestVideoRv.adapter =
                            SuggestVideoAdapter(this@YoutubeVideoActivity, newList) {
                                player.pause()
                            }
                    } else {
                        "Response error".log()
                    }
                }

                override fun onFailure(call: Call<SuggestVideo>, t: Throwable) {
                    "on Failure Suggest video: ${t.localizedMessage}".log()
                }
            })
    }

    fun apiCalling() {

        val bodyModel = VideoBodyModel(
            cpn = "G-Fp1nj33qyL1r8K",
            contentCheckOk = true,
            context = com.example.hdvideoplayer_youtube.ModelData.VideoModel.Context(
                com.example.hdvideoplayer_youtube.ModelData.VideoModel.Client(
                    androidSdkVersion = 31,
                    osVersion = "12",
                    hl = "en-GB",
                    clientName = "ANDROID",
                    gl = "US",
                    clientVersion = "17.31.35",
                    osName = "Android",
                    platform = "MOBILE"
                ),
                com.example.hdvideoplayer_youtube.ModelData.VideoModel.User(lockedSafetyMode = false)
            ),
            racyCheckOk = true,
            videoId = videoId,
            params = "CgIQBg"
        )

        val apiInterface = getVideoRetrofit().create(TrendingInterface::class.java)
        apiInterface.videoData(bodyModel = bodyModel).enqueue(object : Callback<VideoModel> {
            override fun onResponse(call: Call<VideoModel>, response: Response<VideoModel>) {

                if (response.isSuccessful && !isDestroyed) {
                    "video is successful".log()
                    if (response.body()!!.playabilityStatus!!.status == "OK") {
                        status = "OK"
                        val dataList = response.body()!!.streamingData!!

                        val dataToUse = if (dataList.formats.isNullOrEmpty()) {
                            dataList.adaptiveFormats
                        } else {
                            formatList = dataList.formats as ArrayList<FormatsItem>
                        }
                        if (dataToUse != null) {
                            "is format available".log()
                            "format list: $formatList".log()
                            formatList.forEach {
                                if (it.qualityLabel == "720p") {
                                    exoplayerVideoUrl = it.url.toString()
                                    exoplayerVideoUrl.log()
                                }
                            }
                        } else {
                            "is formate not availabel".log()
                        }
                        binding.mainLayout.visible()
                        binding.videoText.gon()
                        "status: ${response.body()!!.playabilityStatus!!.status}".log()
                        suggestVideoApi()

                        if (formatList.isEmpty()) {
                            "is empty".log()
                            binding.imgDownload.gon()
                            binding.txtdownload.gon()
                        } else {
                            "is not empty".log()
                            binding.imgDownload.visible()
                            binding.txtdownload.visible()
                        }

                        createPlayer(exoplayerVideoUrl)
                        title.text = response.body()!!.videoDetails!!.title
                        list.clear()
                        binding.txtTitle.text = response.body()!!.videoDetails!!.title

                        videoThumbnail =
                            response.body()!!.videoDetails!!.thumbnail!!.thumbnails!![2]!!.url.toString()
                        videoTitle = response.body()!!.videoDetails!!.title.toString()
                        cleanedTitle = cleanVideoTitle(videoTitle)
                        "videoTitle: $cleanedTitle".log()
                        list =
                            response.body()!!.streamingData!!.adaptiveFormats as ArrayList<AdaptiveFormatsItem>
                    } else {
                        status = "FAIL"
                        binding.mainLayout.gon()
                        binding.videoText.visible()
                        "status: ${response.body()!!.playabilityStatus!!.status}".log()
                    }
                }
            }

            override fun onFailure(call: Call<VideoModel>, t: Throwable) {
                "Error : ${t.message}".log()
            }
        })
    }

    fun cleanVideoTitle(videoTitle: String): String {
        // Remove special characters, forward slashes, and emojis
        val cleanedTitle = videoTitle.replace("[^a-zA-Z0-9\\s]+".toRegex(), "").replace("/", "")
            .replace("[\\p{InEmoticons}&&\\p{Print}]", "")

        // Replace multiple spaces with a single space
        return cleanedTitle.replace("\\s+".toRegex(), " ")
    }

    override fun onPause() {
        super.onPause()
        if (status == "OK") {
            player.pause()
        } else {
            status.log()
        }
    }

    override fun onDestroy() {
        if (status == "OK") {
            player.release()
        } else {
            status.log()
        }
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

            widthPercentage = 0.70
            val desiredHeight = (widthPercentage * device_width).toInt()
            val layoutParams = binding.exoplayerView.layoutParams
            layoutParams.height = desiredHeight
            binding.exoplayerView.layoutParams = layoutParams
        } else {
            finish()
        }
    }
}