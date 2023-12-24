package com.example.instagram.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.instagram.data.entity.UserLogin
import com.example.instagram.data.remote.AuthResponse
import com.example.instagram.data.remote.AuthService
import com.example.instagram.data.remote.Result
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.ui.main.MainActivity
import com.example.instagram.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() , LoginView{
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        clickBtn()

        setContentView(binding.root)
    }

    private fun clickBtn() {
        binding.loginSignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.loginLoginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, " 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
        if (binding.loginPwdEt.toString().isEmpty()) {
            Toast.makeText(this, " 비밀번호을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }

        val id : String = binding.loginIdEt.text.toString()
        val pwd : String = binding.loginPwdEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(UserLogin(id,pwd))
    }
    private fun startMainActivity() {
        val intent =Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun saveJwt(jwt:String) {
        val spf = getSharedPreferences("userInfo", MODE_PRIVATE)
        Log.d("input Jwt", jwt)
        val editor = spf.edit()
        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onLoginSuccess(result: Result?) {
        startMainActivity()
        saveJwt(result?.jwt.toString())
    }

    override fun onLoginFailure(res: AuthResponse) {
        Toast.makeText(this, res.returnMsg, Toast.LENGTH_SHORT).show()
    }
}