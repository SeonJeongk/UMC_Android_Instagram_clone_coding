package com.example.instagram.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagram.data.entity.User
import com.example.instagram.data.remote.AuthResponse
import com.example.instagram.data.remote.AuthService
import com.example.instagram.databinding.ActivitySignupBinding
import com.example.instagram.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity(), SignUpView {
    private lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        clickbtn()


        setContentView(binding.root)
    }

    private fun clickbtn() {
        binding.signupLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signupSignupBtn.setOnClickListener {
            signUp()
        }
    }

    private fun getUser(): User {
        val id:String = binding.signupIdEt.text.toString()
        val name : String = binding.signupNameEt.text.toString()
        var pwd :String = binding.signupPwdEt.text.toString()
        val email : String = binding.signupEmailEt.text.toString()
        return User(id, pwd, name, email)
    }

    private fun signUp() {
        if (binding.signupIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.signupNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }


        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())
    }
    override fun onSignUpSuccess() {
        finish()
    }
    override fun onSignUpFailure(res : AuthResponse) {
        Toast.makeText(this, res.returnMsg, Toast.LENGTH_SHORT).show()
    }
}