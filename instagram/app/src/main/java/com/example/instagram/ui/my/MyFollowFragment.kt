package com.example.instagram.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentMyFollowBinding
import com.example.instagram.ui.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator


class MyFollowFragment : Fragment() {
    private lateinit var binding : FragmentMyFollowBinding

    private val information = arrayListOf("팔로워", "팔로잉")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFollowBinding.inflate(layoutInflater)

//        var tabNum = arguments?.getInt("tabNum")

        // ViewPager Adapter 연결
        val myFollowVPAdapter = MyFollowVPAdapter(this)
        binding.myFollowContentVp.adapter = myFollowVPAdapter

        TabLayoutMediator(binding.myFollowContentTb, binding.myFollowContentVp) {
                tab, position ->
            tab.text = information[position]
        }.attach()

        clickListener()

        return binding.root
    }
    private fun clickListener() {
        binding.myFollowBackIv.setOnClickListener {
            changeFragmentToMy()
        }
    }

    private fun changeFragmentToMy() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, MyFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }
}