package com.example.equinoxlab.model

import com.example.equinoxlab.database.entity.ManagerEntity
import com.google.gson.annotations.SerializedName

data class ResponseData (

	@SerializedName("CODE") val code : Int,
	@SerializedName("DATA") val data : List<ManagerEntity>,
	@SerializedName("MESSAGE") val message : String
)