package com.example.instagram.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.FragmentMyTagBinding

class MyTagFragment : Fragment() {
    private lateinit var binding : FragmentMyTagBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyTagBinding.inflate(layoutInflater)

        return binding.root
    }
}