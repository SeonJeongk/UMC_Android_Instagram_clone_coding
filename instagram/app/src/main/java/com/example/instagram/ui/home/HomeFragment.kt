package com.example.instagram.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
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
        val postRVAdapter = PostRVAdapter()

        binding.homeStoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeStoryRv.adapter = storyRVAdapter

        binding.homePostRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.homePostRv.adapter = postRVAdapter
    }
}