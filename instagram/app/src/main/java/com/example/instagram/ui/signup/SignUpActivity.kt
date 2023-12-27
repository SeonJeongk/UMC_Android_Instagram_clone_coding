package com.example.instagram.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.data.entity.User
import com.example.instagram.databinding.ActivitySignupBinding
import com.example.instagram.ui.login.LoginActivity
import com.example.instagram.utils.loading.LoadingDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        dialog = LoadingDialog(this)
        clickbtn()

        setContentView(binding.root)
    }

    private fun inputUserInfo(user: User?) {
        user?.let {
            FirebaseFirestore.getInstance()
                .collection("clients")
                .document(user.uid)
                .set(it)
                .addOnSuccessListener {
                    Toast.makeText(this, "정보 삽입 완료", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "정보 삽입 실패", Toast.LENGTH_LONG).show()
                }
        }
    }
    private fun createUser() {
        dialog.show()
        val id:String = binding.signupIdEt.text.toString()
        val name:String = binding.signupNameEt.text.toString()
        val username:String = binding.signupUsernameEt.text.toString()
        var pwd :String = binding.signupPwdEt.text.toString()
        auth.createUserWithEmailAndPassword(id, pwd)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    inputUserInfo(task.result.user?.let { User(id,name,null,null, it.uid, username ) })
                    dialog.dismiss()
                    finish()
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
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