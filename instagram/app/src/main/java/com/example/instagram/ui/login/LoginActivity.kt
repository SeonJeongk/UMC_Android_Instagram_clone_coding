package com.example.instagram.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.instagram.data.entity.User
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.ui.home.PostRVAdapter
import com.example.instagram.ui.main.MainActivity
import com.example.instagram.ui.signup.SignUpActivity
import com.example.instagram.utils.loading.LoadingDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        dialog = LoadingDialog(this)
        auth = FirebaseAuth.getInstance()

        clickBtn()

        setContentView(binding.root)
    }

    private fun login() {
        val id : String = binding.loginIdEt.text.toString()
        val pwd : String = binding.loginPwdEt.text.toString()
        auth.signInWithEmailAndPassword(id, pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    saveUid(user!!.uid)
                    startMainActivity()
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun clickBtn() {
        binding.loginSignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.loginLoginBtn.setOnClickListener {
            dialog.show()
            login()
        }
    }

    private fun startMainActivity() {
        val intent =Intent(this, MainActivity::class.java)
        dialog.dismiss()
        startActivity(intent)
        finish() // LoginActivity를 종료하여 뒤로 가기 버튼으로 돌아가지 않도록 함
    }
    private fun saveUid(uid:String) {
        val spf = getSharedPreferences("userInfo", MODE_PRIVATE)
        Log.d("input uid", uid)
        val editor = spf.edit()
        editor.putString("uid", uid)
        editor.apply()
    }
}