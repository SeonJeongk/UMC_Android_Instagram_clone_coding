package com.example.instagram.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PostVPAdapter(private val context: Context, private val imageList: ArrayList<Int>) : FragmentStateAdapter(context as FragmentActivity) {
    // 게시글 이미지 ViewPager2 adapter
    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment {
        return PostImageFragment.newInstance(imageList[position])   // 이미지를 넘겨준다
    }
}
