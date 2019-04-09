package com.ajaring_kotlin.model

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("updated_on")
	val updatedOn: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("device_id")
	val deviceId: String? = null,

	@field:SerializedName("device_type")
	val deviceType: String? = null,

	@field:SerializedName("licence_image_path")
	val licenceImagePath: String? = null,

	@field:SerializedName("auth_key")
	val authKey: String? = null,

	@field:SerializedName("language_type")
	val languageType: String? = null,

	@field:SerializedName("identity_image_path")
	val identityImagePath: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("lname")
	val lname: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("reset_psw_status")
	val resetPswStatus: String? = null,

	@field:SerializedName("identity_proof")
	val identityProof: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("created_on")
	val createdOn: String? = null,

	@field:SerializedName("document_verification")
	val documentVerification: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("cust_image_path")
	val custImagePath: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("licence_image")
	val licenceImage: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("customer_image")
	val customerImage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)