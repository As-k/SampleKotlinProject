package com.ajaring_kotlin.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class StandardResult(

	@field:SerializedName("data")
	val data: JsonElement? = null,

	@field:SerializedName("error")
	val error: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)