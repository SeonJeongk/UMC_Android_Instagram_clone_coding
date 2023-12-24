package com.example.instagram.ui.my

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentMyBinding
import com.example.instagram.databinding.FragmentReelsBinding
import com.example.instagram.ui.main.MainActivity
import com.example.instagram.ui.search.SearchDatailFragment
import com.google.android.material.tabs.TabLayoutMediator

class MyFragment : Fragment() {
    private lateinit var binding : FragmentMyBinding

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

        // ViewPager Adapter 연결
        val myVPAdapter = MyVPAdapter(this)
        binding.myContentVp.adapter = myVPAdapter

        TabLayoutMediator(binding.myContentTb, binding.myContentVp) {
            tab, position ->
            tab.setIcon(information[position])
        }.attach()

        profileEdit()

        clickListener()

        return binding.root
    }
    private fun profileEdit() {
        var name = arguments?.getString("name")
        var username = arguments?.getString("username")
        var info = arguments?.getString("info")

        if(name != null) {
            binding.myUsername2Tv.text = name
            binding.myUsernameTv.text = username
            binding.myIntroductionTv.text = info
        }
    }

    private fun clickListener() {
        binding.myProfileEditTv.setOnClickListener {
            changeFragmentToProfileEdit()
        }
    }
    private fun changeFragmentToProfileEdit() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, MyProfileEditFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }
}