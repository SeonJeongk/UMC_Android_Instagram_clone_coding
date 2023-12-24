package com.example.instagram.ui.my

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.data.entity.User
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentMyBinding
import com.example.instagram.databinding.FragmentReelsBinding
import com.example.instagram.ui.main.MainActivity
import com.example.instagram.ui.search.SearchDatailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class MyFragment : Fragment() {
    private lateinit var binding : FragmentMyBinding
    private var uid : String? = ""
    private val information = arrayListOf(
        R.drawable.ic_vector,
        R.drawable.ic_union
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(layoutInflater)

        uid = arguments?.getString("uid")
        Log.d("uid",uid.toString())

        // ViewPager Adapter 연결
        val myVPAdapter = MyVPAdapter(this)
        binding.myContentVp.adapter = myVPAdapter

        TabLayoutMediator(binding.myContentTb, binding.myContentVp) {
            tab, position ->
            tab.setIcon(information[position])
        }.attach()
        getUserInfo(uid.toString())

        clickListener()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getUserInfo(uid.toString())  //새로 가져오기
    }
    private fun getUserInfo(uid : String){
        val db = FirebaseFirestore.getInstance()

        val documentRef = db.collection("clients").document(uid)

        documentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val user: User? = documentSnapshot.toUser()

                if (user != null) {
                    // 사용자 객체를 사용할 수 있습니다.

                    Log.d("Firestore", user.toString())
                    profileEdit(user)
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
    private fun profileEdit(user:User) {
        if (user.profile_img != null) {
            Glide.with(this)
                .load(user.profile_img) // 불러올 이미지 url
                .circleCrop() // 동그랗게 자르기
                .into(binding.myProfileImageIv) // 이미지를 넣을 뷰
        }
        binding.myUsername2Tv.text = user.name
        binding.myUsernameTv.text = user.username
        binding.myIntroductionTv.text = user.profile_info
    }

    private fun clickListener() {
        binding.myProfileEditTv.setOnClickListener {
            changeFragmentToProfileEdit()
        }
        binding.myFollowerLl.setOnClickListener {
            changeFragmentToFollow()
        }
        binding.myFollowingLl.setOnClickListener {
            changeFragmentToFollow()

        }
    }
    private fun changeFragmentToProfileEdit() {

        val bundle = Bundle().apply {
            putString("uid", uid)
        }
        val profileEditFragment = MyProfileEditFragment()
        // 프래그먼트에 번들 설정
        profileEditFragment.arguments = bundle

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, profileEditFragment).addToBackStack(tag)
            .commitAllowingStateLoss()
    }

    private fun changeFragmentToFollow() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame,MyFollowFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }

//    private fun changeFragmentToFollow(tabNum: Int) {
//        (context as MainActivity).supportFragmentManager.beginTransaction()
//            .replace(R.id.main_frame, MyFollowFragment().apply {
//                arguments = Bundle().apply {
//                    putInt("tabNum", tabNum)
//                }
//            })
//            .commitAllowingStateLoss()
//    }
}