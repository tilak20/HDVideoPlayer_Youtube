package com.example.hdvideoplayer_youtube.ModelData.SearchApi

import com.google.gson.annotations.SerializedName

data class SearchApiBody(

	@field:SerializedName("context")
	val context: Context? = null,

	@field:SerializedName("continuation")
	val continuation: String? = null
)

data class Client(

	@field:SerializedName("hl")
	val hl: String? = null,

	@field:SerializedName("gl")
	val gl: String? = null,

	@field:SerializedName("clientName")
	val clientName: String? = null,

	@field:SerializedName("originalUrl")
	val originalUrl: String? = null,

	@field:SerializedName("clientVersion")
	val clientVersion: String? = null,

	@field:SerializedName("platform")
	val platform: String? = null
)

data class Context(

	@field:SerializedName("request")
	val request: Request? = null,

	@field:SerializedName("client")
	val client: Client? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class Request(

	@field:SerializedName("internalExperimentFlags")
	val internalExperimentFlags: List<Any?>? = null,

	@field:SerializedName("useSsl")
	val useSsl: Boolean? = null
)

data class User(

	@field:SerializedName("lockedSafetyMode")
	val lockedSafetyMode: Boolean? = null
)
