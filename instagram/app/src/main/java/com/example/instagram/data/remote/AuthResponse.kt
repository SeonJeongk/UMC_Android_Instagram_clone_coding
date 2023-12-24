package com.example.instagram.data.remote

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName(value = "isSuccess")val isSuccess:Boolean,
    @SerializedName(value = "returnCode")val returnCode:String,
    @SerializedName(value = "returnMsg")val returnMsg:String,
    @SerializedName(value = "result") val result : Result?
)

data class Result(
    @SerializedName(value = "userIdx")var userIdx:Int,
    @SerializedName(value = "jwt")var jwt:String,
)
