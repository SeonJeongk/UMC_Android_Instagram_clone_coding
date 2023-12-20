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
import com.example.instagram.ui.main.MainActivity

class SearchDatailFragment : Fragment() {
    private lateinit var binding : FragmentSearchDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchDetailBinding.inflate(layoutInflater)

        isWriting()
        clickDelete()
        clickCancel()
        inputEnter()

        return binding.root
    }
    private fun inputEnter() {
        binding.searchDetailEt.setOnKeyListener{_, keyCode, event->
            if ((event.action== KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Log.d("search", binding.searchDetailEt.text.toString())
                true
            } else {
                false
            }
        }
    }
    private fun isWriting() {
        if(binding.searchDetailEt.text != null) {
            binding.searchDetailDeleteIv.visibility = View.VISIBLE
        }
    }
    private fun clickDelete() {
        binding.searchDetailDeleteIv.setOnClickListener {
            binding.searchDetailEt.setText(null)
        }
    }

    private fun clickCancel() {
        binding.searchDetailCancelTv.setOnClickListener{
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