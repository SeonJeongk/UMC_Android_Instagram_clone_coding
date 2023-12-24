package com.example.instagram.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "userId") var userId :String,
    @SerializedName(value = "userPwd") var userPwd:String,
    @SerializedName(value = "userName") var userName:String?,
    @SerializedName(value = "userEmail") var userEmail:String?,
)
