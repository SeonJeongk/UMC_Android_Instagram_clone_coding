package com.example.instagram.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentMyProfileEditBinding

class MyProfileEditFragment : Fragment() {
    private lateinit var binding : FragmentMyProfileEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileEditBinding.inflate(layoutInflater)

        return binding.root
    }
}