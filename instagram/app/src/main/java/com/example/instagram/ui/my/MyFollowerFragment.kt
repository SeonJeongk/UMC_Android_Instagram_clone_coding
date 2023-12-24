package com.example.instagram.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentMyFollowerBinding

class MyFollowerFragment : Fragment() {
    private lateinit var binding : FragmentMyFollowerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFollowerBinding.inflate(layoutInflater)

        return binding.root
    }
}