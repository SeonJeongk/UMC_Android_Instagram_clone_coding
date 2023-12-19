package com.example.instagram.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.ui.home.HomeFragment
import com.example.instagram.ui.my.MyFragment
import com.example.instagram.ui.reels.ReelsFragment
import com.example.instagram.ui.search.SearchFragment
import com.example.instagram.ui.shop.ShopFragment
import com.example.instagram.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var isVisible = false //password visibility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        clickSignUp()

        setContentView(binding.root)
    }

    private fun clickSignUp() {
        binding.loginSignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}