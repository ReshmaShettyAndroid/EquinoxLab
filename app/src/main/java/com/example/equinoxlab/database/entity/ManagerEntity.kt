package com.example.equinoxlab.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "manager")
data class ManagerEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "dept_name") var dept_name: String
//        @ColumnInfo(name = "TODAYS_COUNT") var TODAYS_COUNT : String = "",
//        @ColumnInfo(name = "age") var age : Int,
//        @ColumnInfo(name = "created_on") var created_on : String,
//        @ColumnInfo(name = "dept_id") var dept_id : String,
//        @ColumnInfo(name = "email_id") var email_id : String,
//        @ColumnInfo(name = "gender") var gender : String,
//        @ColumnInfo(name = "is_deleted") var is_deleted : String,
//        @ColumnInfo(name = "mobile") var mobile : String,
//        @ColumnInfo(name = "password") var password : String,
//        @ColumnInfo(name = "profile_img") var profile_img : String,
//        @ColumnInfo(name = "reporting_to_id") var reporting_to_id : String,
//        @ColumnInfo(name = "reporting_to_name") var reporting_to_name : String,
//        @ColumnInfo(name = "role_id") var role_id : String,
//        @ColumnInfo(name = "role_name") var role_name : String,
//        @ColumnInfo(name = "token") var token : String,
//        @ColumnInfo(name = "updated_on") var updated_on : String,
//        @ColumnInfo(name = "user_id") var user_id : String
        )