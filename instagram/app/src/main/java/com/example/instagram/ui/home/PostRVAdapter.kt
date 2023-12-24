package com.example.instagram.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.data.entity.ImageItem
import com.example.instagram.databinding.ItemHomePostBinding
import me.relex.circleindicator.CircleIndicator3

class PostRVAdapter() : RecyclerView.Adapter<PostRVAdapter.ViewHolder>() {

    // 더미 데이터
    val profileImgData = arrayOf(
        ImageItem(R.drawable.post_abata_img),
        ImageItem(R.drawable.reels_profile_1),
        ImageItem(R.drawable.home_profile_ex1),
        ImageItem(R.drawable.home_profile_ex2),
        ImageItem(R.drawable.home_profile_ex3)
    )
    val nameData = arrayOf( "profile_1", "profile_2", "profile_3", "profile_4", "profile_5" )
    val likeNum = arrayOf( "30,278", "345,689", "56", "146", "789" )
    val commentNum = arrayOf( "1,234", "2,467", "10", "39", "368" )
    val contents = arrayOf( "우산 쓰고 이쁘지", "강아지 귀엽지", "나 치명적이지", "머리잘랐다^^", "냅다 귀엽지" )
    val date = arrayOf( "10분 전", "1시간 전", "4시간 전", "12월 23일", "12월 20일")

    // 각 position 별로 이미지 List를 다르게 넘겨준다
    val postImageLists = arrayListOf(
        arrayListOf(R.drawable.post_1, R.drawable.post_12, R.drawable.post_14),
        arrayListOf(R.drawable.post_12, R.drawable.post_6),
        arrayListOf(R.drawable.post_6, R.drawable.post_12, R.drawable.post_11),
        arrayListOf(R.drawable.post_11),
        arrayListOf(R.drawable.post_14, R.drawable.post_1, R.drawable.post_12)
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostRVAdapter.ViewHolder {
        val binding: ItemHomePostBinding = ItemHomePostBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false)

        return ViewHolder(binding)  //item view 객체 return
    }

    override fun getItemCount(): Int = profileImgData.size

    override fun onBindViewHolder(holder: PostRVAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    //item view 객체를 담고 있는 viewHolder
    inner class ViewHolder(val binding: ItemHomePostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.itemHomePostProfileIv.setImageResource(profileImgData[position].imageResourceId)    // 프로필 이미지
            binding.itemHomePostCommentProfileIv.setImageResource(profileImgData[position].imageResourceId)
            binding.itemHomePostNameTv.text = nameData[position]    // 유저명
            binding.itemHomePostContentsNameTv.text = nameData[position]
            binding.itemHomePostLikeNumTv.text = likeNum[position]    // 좋아요 수
            binding.itemHomePostCommentNumTv.text = commentNum[position]    // 댓글 수
            binding.itemHomePostContentsTv.text = contents[position]    // 본문 내용
            binding.itemHomePostDateTv.text = date[position]    // 날짜

            // 게시글 이미지 ViewPager와 indicator 설정
            val postAdapter = PostVPAdapter(itemView.context, postImageLists[position])
            val indicator: CircleIndicator3 = binding.itemHomePostIndicator
            indicator.setViewPager(binding.itemHomePostImgVp)

            binding.itemHomePostImgVp.adapter = postAdapter    // ViewPager에 연결
            binding.itemHomePostIndicator.setViewPager(binding.itemHomePostImgVp)   // indicator 연결
        }
    }
}