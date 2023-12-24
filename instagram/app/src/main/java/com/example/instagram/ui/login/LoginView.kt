package com.example.instagram.ui.login

import com.example.instagram.data.remote.Result
import com.example.instagram.data.remote.AuthResponse

interface LoginView {
    fun onLoginSuccess(result: Result?)
    fun onLoginFailure(res : AuthResponse)
}