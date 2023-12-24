package com.example.instagram.ui.my

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.data.entity.User
import com.example.instagram.databinding.FragmentMyProfileEditBinding
import com.example.instagram.ui.main.MainActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MyProfileEditFragment : Fragment() {
    private lateinit var binding : FragmentMyProfileEditBinding
    private val GALLARY_CODE:Int= 10
    private lateinit var uri: Uri
    private var uid : String? = ""
    private var img : String? = ""
    private lateinit var storage : FirebaseStorage
    private lateinit var user:User
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileEditBinding.inflate(layoutInflater)

        uid = arguments?.getString("uid")
        Log.d("uid",uid.toString())
        uid?.let { getUserInfo(it) }

        storage = FirebaseStorage.getInstance()
        clickListener()

        return binding.root
    }

    private fun clickListener() {

        binding.myProfileCloseIv.setOnClickListener {
            changeFragmentToMy()
        }

        binding.myProfileSelectIv.setOnClickListener {
            user.name = binding.myProfileNameEt.text.toString()
            user.username = binding.myProfileUsernameEt.text.toString()
            user.profile_info = binding.myProfileInfoEt.text.toString()
            imageUpload(uri)
            changeFragmentToMy()
        }

        binding.myProfileEditTv.setOnClickListener {
            val imagePick = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            registerForActivityResult.launch(imagePick)
        }
    }
    private fun setEtText() {
        if (user.profile_img != null) {
            Glide.with(this)
                .load(user.profile_img) // 불러올 이미지 url
                .circleCrop() // 동그랗게 자르기
                .into(binding.myProfileEtImageIv) // 이미지를 넣을 뷰
        }
        binding.myProfileNameEt.setText(user!!.name)
        binding.myProfileUsernameEt.setText(user!!.username)
        binding.myProfileInfoEt.setText(user!!.profile_info)
    }
    private fun getUserInfo(uid : String){
        val db = FirebaseFirestore.getInstance()

        val documentRef = db.collection("clients").document(uid)

        documentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                user = documentSnapshot.toUser()!!

                if (user != null) {
                    // 사용자 객체를 사용할 수 있습니다.
                    Log.d("Firestore", user.toString())
                    setEtText()
                } else {
                    // 변환에 실패하거나 문서가 존재하지 않는 경우
                    Log.d("Firestore", "Failed to convert DocumentSnapshot to User")
                }
            }
            .addOnFailureListener { exception ->
                // 오류 처리
                Log.e("Firestore", "Error getting document: ", exception)
            }
    }
    fun DocumentSnapshot.toUser(): User? {
        return try {
            val uid = getString("uid") ?: ""
            val username = getString("username") ?: ""
            val name = getString("name") ?: ""
            val email = getString("email") ?: ""
            val profile_img = getString("profile_img") ?: ""
            val profile_info = getString("profile_info") ?: ""

            User(email, name, profile_img, profile_info, uid, username)
        } catch (e: Exception) {
            null // 변환에 실패한 경우 null 반환 또는 예외 처리를 수행할 수 있습니다.
        }
    }

    private fun setUserInfo(uid : String){
        val db = FirebaseFirestore.getInstance()

        val documentRef = db.collection("clients").document(uid)

        val newData = mapOf(
            "name" to user!!.name,
            "username" to user!!.username,
            "profile_info" to user!!.profile_info,
            "profile_img" to img,
        )

        documentRef.update(newData)
            .addOnSuccessListener {
                Log.d("Firebase", "Document updated successfully!")
            }
            .addOnFailureListener { exception ->
                // 오류 처리
                Log.e("Firestore", "Error getting document: ", exception)
            }
    }

    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                AppCompatActivity.RESULT_OK -> {
                    // 변수 uri에 전달 받은 이미지 uri를 넣어준다.
                    uri = result.data?.data!!
                    // 이미지를 ImageView에 표시한다.
                    binding.myProfileEtImageIv.setImageURI(uri)
                }
            }
        }

    private fun imageUpload(uri: Uri) {
        // storage 참조
        val storageRef = storage.getReference("image")
        // storage에 저장할 파일명 선언
        val fileName = uid
        val mountainsRef = storageRef.child("${fileName}.png")

        // 기존 파일 삭제
        mountainsRef.delete().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // 파일 삭제 성공
                // 새로운 파일 업로드
                val uploadTask = mountainsRef.putFile(uri)
                uploadTask.addOnSuccessListener { taskSnapshot ->
                    // 파일 업로드 성공
                    Toast.makeText(getActivity(), "사진 업로드 성공", Toast.LENGTH_SHORT).show()
                    mountainsRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        img = downloadUri.toString()
                        Log.d("img", img.toString())
                        setUserInfo(uid.toString())
                    }.addOnFailureListener { e ->
                        // 파일 업로드 실패
                        Toast.makeText(getActivity(), "사진 업로드 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // 파일 삭제 실패->파일 없음
                Toast.makeText(getActivity(), "파일 삭제 실패", Toast.LENGTH_SHORT).show()
                val uploadTask = mountainsRef.putFile(uri)
                uploadTask.addOnSuccessListener { taskSnapshot ->
                    // 파일 업로드 성공
                    Toast.makeText(getActivity(), "사진 업로드 성공", Toast.LENGTH_SHORT).show()
                    mountainsRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        img = downloadUri.toString()
                        Log.d("img", img.toString())
                        setUserInfo(uid.toString())
                    }.addOnFailureListener { e ->
                        // 파일 업로드 실패
                        Toast.makeText(getActivity(), "사진 업로드 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun changeFragmentToMy() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, MyFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }

}