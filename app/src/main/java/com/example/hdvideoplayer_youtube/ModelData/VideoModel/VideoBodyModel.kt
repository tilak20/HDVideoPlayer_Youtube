package com.example.hdvideoplayer_youtube.ModelData.VideoModel

import com.google.gson.annotations.SerializedName

data class VideoBodyModel(

	@field:SerializedName("cpn")
	val cpn: String? = null,

	@field:SerializedName("contentCheckOk")
	val contentCheckOk: Boolean? = null,

	@field:SerializedName("context")
	val context: Context? = null,

	@field:SerializedName("racyCheckOk")
	val racyCheckOk: Boolean? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class User(

	@field:SerializedName("lockedSafetyMode")
	val lockedSafetyMode: Boolean? = null
)

data class Client(

	@field:SerializedName("androidSdkVersion")
	val androidSdkVersion: Int? = null,

	@field:SerializedName("osVersion")
	val osVersion: String? = null,

	@field:SerializedName("hl")
	val hl: String? = null,

	@field:SerializedName("clientName")
	val clientName: String? = null,

	@field:SerializedName("gl")
	val gl: String? = null,

	@field:SerializedName("clientVersion")
	val clientVersion: String? = null,

	@field:SerializedName("osName")
	val osName: String? = null,

	@field:SerializedName("platform")
	val platform: String? = null
)

data class Context(

	@field:SerializedName("client")
	val client: Client? = null,

	@field:SerializedName("user")
	val user: User? = null
)
