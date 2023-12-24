package com.example.instagram.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserLoginTable")
data class UserLogin(
    @SerializedName(value = "userInfo") var userInfo :String,
    @SerializedName(value = "userPwd") var userPwd:String,
)
