package com.example.hdvideoplayer_youtube.Activity

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import com.example.hdvideoplayer_youtube.Adapter.SearchVideoAdapter
import com.example.hdvideoplayer_youtube.ModelData.Video
import com.example.hdvideoplayer_youtube.R
import com.example.hdvideoplayer_youtube.Utils.gon
import com.example.hdvideoplayer_youtube.Utils.visible
import com.example.hdvideoplayer_youtube.databinding.ActivitySearchVideoBinding
import com.google.ads.sdk.AdsManager
import java.io.File
import java.util.Locale

class SearchVideoAct : BaseAct<ActivitySearchVideoBinding>() {
    val searchList: ArrayList<Video> = arrayListOf()
    var position: Int = 0
    lateinit var searchVideoAdapter: SearchVideoAdapter

    companion object {
        var SearchvideoList: ArrayList<Video> = arrayListOf()
    }


    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivitySearchVideoBinding.inflate(layoutInflater)

    override fun initUI() {

        AdsManager.getInstance().showNativeSmall(binding.nativeads, R.layout.ad_unified)

        getAllPlayableVideos()

        binding.apply {

            imgback.setOnClickListener {
                AdsManager.getInstance().showOnbackPressAdExtra(this@SearchVideoAct) { finish() }
            }

            SearchView.queryHint = "Search here"
            SearchView.isIconified = false

            if (SearchvideoList.size == 0) {
                rcView.gon()
                imgEmpty.visible()
            } else {
                rcView.visible()
                imgEmpty.gon()
            }


            SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    searchList.clear()


                    for (i in SearchvideoList) {
                        if (i.videoName.lowercase()
                                .contains(newText!!.lowercase(Locale.getDefault()))
                        ) {
                            searchList.add(i)
                        }
                    }

                    if (searchList.size == 0) {
                        rcView.gon()
                        imgEmpty.visible()
                    } else {
                        rcView.visible()
                        imgEmpty.gon()
                    }
                    searchVideoAdapter.SearchData(searchList)

                    return true
                }
            })
        }

    }

    fun getAllPlayableVideos() {
        SearchvideoList.clear()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DESCRIPTION,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.ARTIST,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID
        )
        val sortOrder = MediaStore.Video.Media.DATE_MODIFIED + " DESC"

        val cursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, sortOrder
        )

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val dateAddesColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val resolutionColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)
            val mimeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)
            val descColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DESCRIPTION)
            val dateTakenColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)
            val bucketNameColumn =
                it.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)

            while (it.moveToNext()) {
                val videoId = it.getLong(idColumn)
                val videoName = it.getString(nameColumn)
                val videoPath = it.getString(dataColumn)
                val videoDuration = it.getInt(durationColumn)
                val videoSize = it.getLong(sizeColumn)
                val videoDateadded = it.getString(dateAddesColumn)
                val videoResolution = it.getInt(resolutionColumn)
                val videoMime = it.getString(mimeColumn)
                val videoTitle = it.getString(titleColumn)
                val videoDescription =
                    if (it.getString(descColumn) == null) "null" else it.getString(descColumn)
                val videoDatetaken =
                    if (it.getString(dateTakenColumn) == null) "null" else it.getString(
                        dateTakenColumn
                    )

                val videoArtist =
                    if (it.getString(artistColumn) == null) "null" else it.getString(artistColumn)
                val videoBucketId =
                    if (it.getString(bucketIdColumn) == null) "null" else it.getString(
                        bucketIdColumn
                    )
                val videoBucket =
                    if (it.getString(bucketNameColumn) == null) videoBucketId else it.getString(
                        bucketNameColumn
                    )
                val videoUri =
                    ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoId)
                        .toString()
                val thumbnail = Uri.fromFile(File(videoPath))


                SearchvideoList.add(
                    Video(
                        videoId,
                        videoName,
                        videoPath,
                        videoDuration,
                        videoSize,
                        videoDateadded,
                        videoResolution,
                        videoMime,
                        videoTitle,
                        videoDescription,
                        videoDatetaken,
                        videoArtist,
                        videoUri,
                        thumbnail.toString(),
                        videoBucket,
                        videoBucketId
                    )
                )

            }
            setRV(SearchvideoList)
        }
    }

    fun setRV(videoList: ArrayList<Video>) {
        searchVideoAdapter = SearchVideoAdapter(this, videoList) { it, it2, view ->
            position = it

            if (it2 == "root") {
                AdsManager.getInstance().showInterstitialAd(this@SearchVideoAct) {
                    startActivity(
                        Intent(this, VideoPlayActivity::class.java).putExtra("position", position)
                            .putExtra("video_title", title).putExtra("video_from", "Search")
                    )
                }
            }
        }
//        val scaleInAnimationAdapter = ScaleInAnimationAdapter(searchVideoAdapter)
//        scaleInAnimationAdapter.setDuration(500)
//        scaleInAnimationAdapter.setInterpolator(OvershootInterpolator())
//        scaleInAnimationAdapter.setFirstOnly(false)
        binding.rcView.adapter = searchVideoAdapter
    }

    override fun onBackPressed() {
        AdsManager.getInstance().showOnbackPressAdExtra(this@SearchVideoAct) { finish() }
    }
}