package com.example.equinoxlab.model

import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("TODAYS_COUNT") val tODAYS_COUNT : String,
	@SerializedName("age") val age : Int,
	@SerializedName("created_on") val created_on : String,
	@SerializedName("dept_id") val dept_id : String,
	@SerializedName("dept_name") val dept_name : String,
	@SerializedName("email_id") val email_id : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("is_deleted") val is_deleted : String,
	@SerializedName("mobile") val mobile : String,
	@SerializedName("name") val name : String,
	@SerializedName("password") val password : String,
	@SerializedName("profile_img") val profile_img : String,
	@SerializedName("reporting_to_id") val reporting_to_id : String,
	@SerializedName("reporting_to_name") val reporting_to_name : String,
	@SerializedName("role_id") val role_id : String,
	@SerializedName("role_name") val role_name : String,
	@SerializedName("token") val token : String,
	@SerializedName("updated_on") val updated_on : String,
	@SerializedName("user_id") val user_id : String
)