package com.example.instagram.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.databinding.ActivitySignupBinding
import com.example.instagram.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        clickbtn()

        setContentView(binding.root)
    }
    private fun createUser() {
        val id:String = binding.signupIdEt.text.toString()
        var pwd :String = binding.signupPwdEt.text.toString()
        auth.createUserWithEmailAndPassword(id, pwd)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
    }
    private fun clickbtn() {
        binding.signupLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signupSignupBtn.setOnClickListener {
            createUser()
        }
    }

}