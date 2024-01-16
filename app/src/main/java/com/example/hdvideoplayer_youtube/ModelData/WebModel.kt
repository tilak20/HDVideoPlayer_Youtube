package com.example.hdvideoplayer_youtube.ModelData

import com.google.gson.annotations.SerializedName

data class WebModel(

	@field:SerializedName("parseType")
	val parseType: String? = null,

	@field:SerializedName("fromType")
	val fromType: String? = null,

	@field:SerializedName("showDialog")
	val showDialog: Boolean? = null,

	@field:SerializedName("fromUrl")
	val fromUrl: String? = null,

	@field:SerializedName("dataList")
	val dataList: List<DataListItem?>? = null,

	@field:SerializedName("dataSource")
	val dataSource: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataListItem(

	@field:SerializedName("sourceUrl")
	val sourceUrl: String? = null,

	@field:SerializedName("fromUrl")
	val fromUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mediaUrlList")
	val mediaUrlList: List<String?>? = null,

	@field:SerializedName("quality")
	val quality: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null
)
