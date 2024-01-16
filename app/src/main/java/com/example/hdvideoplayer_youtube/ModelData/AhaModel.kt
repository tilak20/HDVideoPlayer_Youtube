package com.example.hdvideoplayer_youtube.ModelData

import androidx.annotation.Keep
import com.downloader.Status
import com.google.gson.annotations.SerializedName

@Keep
data class AhaModel(
    var status: String = "time_line",
    var fromType: String = "",
    var showDialog: Boolean = false,
    var parseType: String = "",
    var dataList: ArrayList<DataList>?,
    var audioList: ArrayList<AudioList>?,
    var fromUrl: String = "",
    var dataSource: String = "",
)

@Keep
data class DataList(
    var name: String = "",
    var sourceUrl: String = "",
    var quality: String = "SD",
    var fromUrl: String = "",
    var thumbnailUrl: String = "",
    var headerReferer: String = "",
    var headerUserAgent: String = "",
    var duration: Int? = 0,
    var mediaUrlList: ArrayList<String>?,
    var videoId: String? = "",
    var filesize: Long = 0L,
    var isSelected: Boolean = false
)

@Keep
data class AudioList(
    var name: String = "",
    var duration: Int? = 0,
    var thumbnailUrl: String = "",
    var sourceUrl: String = "",
    var fromUrl: String = "",
    var headerReferer: String = "",
    var headerUserAgent: String = "",
    var mediaUrlList: ArrayList<String>?,
)
