package com.example.instagram.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentReelsBinding
import com.example.instagram.databinding.FragmentSearchBinding
import com.example.instagram.databinding.FragmentSearchDetailBinding
import com.example.instagram.databinding.FragmentSearchTabBinding
import com.example.instagram.ui.main.MainActivity

class SearchTabFragment : Fragment() {
    private lateinit var binding : FragmentSearchTabBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchTabBinding.inflate(layoutInflater)
        clickListener()

        return binding.root
    }

    private fun clickListener() {
        binding.searchTabBackBtn.setOnClickListener{
            changeFragment()
        }
    }

    private fun changeFragment() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, SearchFragment().apply {
            })
            .commitAllowingStateLoss()
    }

}