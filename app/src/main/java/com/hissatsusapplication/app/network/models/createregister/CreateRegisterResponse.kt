package com.hissatsusapplication.app.network.models.createregister

import com.google.gson.annotations.SerializedName

data class CreateRegisterResponse(

	@field:SerializedName("data")
	val data: CreateRegisterResponseData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class CreateRegisterResponseData(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("isDeleted")
	val isDeleted: Boolean? = null,

	@field:SerializedName("profile")
	val profile: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("userType")
	val userType: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("isActive")
	val isActive: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("loginRetryLimit")
	val loginRetryLimit: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
