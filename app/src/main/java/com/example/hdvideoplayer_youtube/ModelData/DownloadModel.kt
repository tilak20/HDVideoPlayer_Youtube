package com.example.hdvideoplayer_youtube.ModelData

import com.downloader.Status
import java.io.File

data class DownloadModel(val url: File, val time: String)
data class Downloading(
    var path: String = "No",
    var progress: Long = 1,
    var id: Int = 0,
    var name: String,
    var src: String = "",
//    val size: String,
    val quality: String,
    var status: Status,
    var currentSize: String
)

data class ProgressData(
    var progress: Long = 1,
    var name: String,
)
