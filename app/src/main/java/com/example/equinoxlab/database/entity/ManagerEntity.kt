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
        )