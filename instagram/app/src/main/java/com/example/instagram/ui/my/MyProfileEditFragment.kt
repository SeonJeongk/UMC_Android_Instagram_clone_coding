package com.example.instagram.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentMyProfileEditBinding
import com.example.instagram.ui.main.MainActivity

class MyProfileEditFragment : Fragment() {
    private lateinit var binding : FragmentMyProfileEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileEditBinding.inflate(layoutInflater)

        clickListener()

        return binding.root
    }

    private fun clickListener() {

        binding.myProfileCloseIv.setOnClickListener {
            changeFragmentToMy()
        }

        binding.myProfileSelectIv.setOnClickListener {
            changeFragmentToMy2()
        }
    }

    private fun changeFragmentToMy() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, MyFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }

    private fun changeFragmentToMy2() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, MyFragment().apply {
                arguments = Bundle().apply {
                    putString("name", binding.myProfileNameEt.text.toString())
                    putString("username", binding.myProfileUsernameEt.text.toString())
                    putString("info", binding.myProfileInfoEt.text.toString())
                }
            })
            .commitAllowingStateLoss()
    }
}