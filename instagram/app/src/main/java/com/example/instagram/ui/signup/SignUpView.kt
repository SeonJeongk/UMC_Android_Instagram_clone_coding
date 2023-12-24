package com.example.instagram.ui.signup

import com.example.instagram.data.remote.AuthResponse

interface SignUpView {
    fun onSignUpSuccess()
    fun onSignUpFailure(res : AuthResponse)
}