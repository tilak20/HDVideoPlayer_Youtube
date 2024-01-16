package com.example.hdvideoplayer_youtube.ModelData

import androidx.annotation.Keep
import java.io.File
import java.util.Date

@Keep
data class UrlModel(
    val url: File,
    val duration: String,
    val width: Int,
    val height: Int,
    val formattedSize: String,
    val lastModifiedDate: Date
)