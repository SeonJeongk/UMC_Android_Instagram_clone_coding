package com.example.instagram.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentMyFollowingBinding


class MyFollowingFragment : Fragment() {
    private lateinit var binding : FragmentMyFollowingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFollowingBinding.inflate(layoutInflater)

        return binding.root
    }
}