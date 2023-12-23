package com.example.instagram.ui.reels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.databinding.FragmentReelsBinding

class ReelsFragment : Fragment() {
    private lateinit var binding : FragmentReelsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReelsBinding.inflate(inflater, container, false)
        initRecyclerview()

        return binding.root
    }

    private fun initRecyclerview(){
        val reelsRVAdapter = ReelsRVAdapter()

        binding.reelsPostRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.reelsPostRv.adapter = reelsRVAdapter

        // LinearSnapHelper를 사용해 스크롤 시 자동으로 가까운 아이템으로 이동
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.reelsPostRv)

        // OnScrollListener를 사용해 현재 아이템 position을 받는다
        binding.reelsPostRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 현재 스크롤 위치
            }
        })
    }
}