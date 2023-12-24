package com.example.instagram.data.remote

import android.util.Log
import com.example.instagram.data.entity.User
import com.example.instagram.data.entity.UserLogin
import com.example.instagram.ui.login.LoginView
import com.example.instagram.ui.signup.SignUpView
import com.example.instagram.utils.getRetrofit
import retrofit2.*
class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView
    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }
    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun signUp(user : User){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.signUp(user).enqueue(object:Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val resp: AuthResponse = response.body()!!
                Log.d("response", resp.toString())

                if(resp.returnCode.toString() == "COMMON200") signUpView.onSignUpSuccess()
                else if(resp.returnCode.toString() == "SIGNUP4001") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "SIGNUP4091") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "SIGNUP4092") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "DB500") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "USER4003") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "USER4004") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "USER4005") signUpView.onSignUpFailure(resp)
                else if(resp.returnCode.toString() == "USER4006") signUpView.onSignUpFailure(resp)
                else signUpView.onSignUpFailure(resp)
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP/FAILURE", t.message.toString())
            }

        })
    }

    fun login(user: UserLogin){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.login(user).enqueue(object:Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val resp: AuthResponse = response.body()!!
                Log.d("response", resp.toString())

                if(resp.returnCode.toString() == "COMMON200") loginView.onLoginSuccess(resp.result)
                else if(resp.returnCode.toString() == "USER4003") loginView.onLoginFailure(resp)
                else if(resp.returnCode.toString() == "DB500") loginView.onLoginFailure(resp)
                else if(resp.returnCode.toString() == "USER4041") loginView.onLoginFailure(resp)
                else if(resp.returnCode.toString() == "SIGNUP4003") loginView.onLoginFailure(resp)
                else if(resp.returnCode.toString() == "LOGIN4041") loginView.onLoginFailure(resp)
                else loginView.onLoginFailure(resp)
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE", t.message.toString())
            }

        })
    }

}