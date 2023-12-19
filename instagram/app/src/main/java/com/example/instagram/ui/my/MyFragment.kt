package com.example.instagram.ui.my

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentMyBinding
import com.example.instagram.databinding.FragmentReelsBinding

class MyFragment : Fragment() {
    private lateinit var binding : FragmentMyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(layoutInflater)

        return binding.root
    }
}