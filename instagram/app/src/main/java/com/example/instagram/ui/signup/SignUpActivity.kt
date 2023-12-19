package com.example.instagram.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.databinding.ActivitySignupBinding
import com.example.instagram.ui.home.HomeFragment
import com.example.instagram.ui.login.LoginActivity
import com.example.instagram.ui.my.MyFragment
import com.example.instagram.ui.reels.ReelsFragment
import com.example.instagram.ui.search.SearchFragment
import com.example.instagram.ui.shop.ShopFragment

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        clickLogin()

        setContentView(binding.root)
    }

    private fun clickLogin() {
        binding.signupLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}