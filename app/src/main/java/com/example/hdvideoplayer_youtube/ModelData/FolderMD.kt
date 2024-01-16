package com.example.hdvideoplayer_youtube.ModelData

import android.net.Uri

class FolderMD(val videoBucketId: String, val videoBucket: String)

class CurrentVideoMd(
    var videoId: Long,
    var videoPath: String,
    var videoDuration: String,
    var videoSize: Long,
    var videoTitle: String,
    var videoBucket: String,
    var videoUri: String,
    var file: Uri
)

class CurrentImageMd(
    var imageId: Long,
    var imageName: String,
    var imagePath: String,
    var imageSize: Long,
    var imageDateadded: Int,
    var imageMime: String,
    var imageTitle: String,
    var imageDatetaken: String,
    var imageBucket: String,
    var imageUri: String,
    var thumbnail: Uri
)

class Video(
    val videoId: Long,
    val videoName: String,
    val videoPath: String,
    val videoDuration: Int,
    val videoSize: Long,
    val videoDateadded: String,
    val videoResolution: Int,
    val videoMime: String,
    val videoTitle: String,
    val videoDescription: String,
    val videoDatetaken: String,
    val videoArtist: String,
    val videoUri: String,
    val thumb: String,
    val videoBucket: String,
    val videoBucketId: String
)

class Image(
    imageId: Long,
    imageName: String,
    imagePath: String,
    imageDuration: Int,
    imageSize: Long,
    imageDateadded: String,
    imageResolution: String,
    imageMime: String,
    imageTitle: String,
    imageDescription: String,
    imageDatetaken: String,
    imageArtist: Int,
    imageUri: String,
    toString: String,
    imageBucket: String,
    imageBucketId: String
)

class WSMData(var path: String, var name: String, var file: String)
class MyStatus(var path: String, var name: String, var uri : Uri)

class VideoModelData(
    imageId: Long,
    imageName: String,
    imagePath: String,
    imageDuration: Int,
    imageSize: Long,
    imageDateadded: String,
    imageResolution: String,
    imageMime: String,
    imageTitle: String,
    imageDescription: String,
    imageDatetaken: String,
    imageArtist: Int,
    imageUri: String,
    toString: String,
    imageBucket: String,
    imageBucketId: String
)