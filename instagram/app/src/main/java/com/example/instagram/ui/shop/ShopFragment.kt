package com.example.instagram.ui.shop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentSearchBinding
import com.example.instagram.databinding.FragmentShopBinding

class ShopFragment : Fragment() {
    private lateinit var binding : FragmentShopBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(layoutInflater)

        return binding.root
    }
}