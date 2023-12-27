package com.example.instagram.ui.home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    private var uid: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerview()

        return binding.root
    }

    private fun initRecyclerview(){
        val storyRVAdapter = StoryRVAdapter()
        var postRVAdapter = PostRVAdapter(requireContext(),null)

        // SharedPreferences에 저장된 uid 가져오기
        val spf = requireContext().getSharedPreferences("userInfo", MODE_PRIVATE)
        uid = spf.getString("uid", null)

        // PostRVAdapter에 uid 전달
        uid?.let {
            postRVAdapter = PostRVAdapter(requireContext(),it)
        }

        binding.homeStoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeStoryRv.adapter = storyRVAdapter

        binding.homePostRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.homePostRv.adapter = postRVAdapter
    }
}