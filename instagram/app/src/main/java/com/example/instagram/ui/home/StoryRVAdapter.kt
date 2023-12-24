package com.example.instagram.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.data.ImageItem
import com.example.instagram.databinding.ItemStoriesBinding

class StoryRVAdapter() : RecyclerView.Adapter<StoryRVAdapter.ViewHolder>() {

    // 더미 데이터
    val profileImgData = arrayOf(
        ImageItem(R.drawable.post_abata_img),
        ImageItem(R.drawable.reels_profile_1),
        ImageItem(R.drawable.home_profile_ex1),
        ImageItem(R.drawable.home_profile_ex2),
        ImageItem(R.drawable.home_profile_ex3)
    )
    val profileBorder = arrayOf( "old", "new", "new", "old", "old" )
    val nameData = arrayOf( "내 스토리", "profile_2", "profile_3", "profile_4", "profile_5" )

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): StoryRVAdapter.ViewHolder {
        val binding: ItemStoriesBinding = ItemStoriesBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false)

        return ViewHolder(binding)  //item view 객체 return
    }

    override fun getItemCount(): Int = profileImgData.size

    override fun onBindViewHolder(holder: StoryRVAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    //item view 객체를 담고 있는 viewHolder
    inner class ViewHolder(val binding: ItemStoriesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.itemStoriesProfileIv.setImageResource(profileImgData[position].imageResourceId)
            binding.itemStoriesNameTv.text = nameData[position]

            if (profileBorder[position] == "old"){  // 봤던 스토리
                binding.itemStoriesBorderOldIv.visibility = View.VISIBLE
                binding.itemStoriesBorderNewIv.visibility = View.GONE
            }else { // 새로운 스토리
                binding.itemStoriesBorderOldIv.visibility = View.GONE
                binding.itemStoriesBorderNewIv.visibility = View.VISIBLE
            }
        }
    }
}