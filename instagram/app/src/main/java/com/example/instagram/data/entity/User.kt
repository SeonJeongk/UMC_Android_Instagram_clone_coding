package com.example.instagram.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class User(
    var email : String,
    var name :String,
    var profile_img :String?,
    var profile_info:String?,
    var uid:String,
    var username:String?,
)
