package com.example.instagram.data.remote

import com.example.instagram.data.entity.User
import com.example.instagram.data.entity.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/api/user/join")
    fun signUp(@Body user: User) : Call<AuthResponse>

    @POST("/api/user/login")
    fun login(@Body user: UserLogin) : Call<AuthResponse>
}