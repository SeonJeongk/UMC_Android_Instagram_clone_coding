package com.example.instagram.ui.reels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.data.ImageItem
import com.example.instagram.databinding.ItemReelsBinding

class ReelsRVAdapter() : RecyclerView.Adapter<ReelsRVAdapter.ViewHolder>() {

    // 더미 데이터
    val reelsData = arrayOf(
        ImageItem(R.drawable.reels_1),
        ImageItem(R.drawable.reels_2)
    )
    val profileImgData = arrayOf(
        ImageItem(R.drawable.post_abata_img),
        ImageItem(R.drawable.reels_profile_1)
    )
    val nameData = arrayOf( "profile_1", "profile_2" )
    val contentsData = arrayOf(
        "인스타그램 클론 코딩 하는중이다. 텍스트 넘어가기 테스트",
        "강아지 귀엽지? 귀여버"
    )
    val likeData = arrayOf( "1.4만", "2.3만" )
    val commentData = arrayOf( "369", "200" )
    val shareData = arrayOf( "2,345", "1,234" )
    val songInfo = arrayOf( "♫ The Kid LAROI, Justin Bieber ⦁ STAY", "♫ profile_2 ⦁ 원본 오디오")
    val songCover = arrayOf(
        ImageItem(R.drawable.reels_song_cover1),
        ImageItem(R.drawable.reels_profile_1)
    )
    // 좋아요, 팔로우 상태를 저장하는 리스트
    private val likeStates = mutableListOf<Boolean>()
    private val followStates = mutableListOf<Boolean>()

    init {
        // 초기 상태 설정
        likeStates.addAll(List(reelsData.size) { false })
        followStates.addAll(List(reelsData.size) { false })
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemReelsBinding = ItemReelsBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false)

        return ViewHolder(binding)  //item view 객체 return
    }

    override fun getItemCount(): Int = reelsData.size

    // 현재 보여지는 item 위치 및 클릭이벤트 설정
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

        holder.binding.itemReelsFollowTv.setOnClickListener{    // 팔로우 버튼
            if(followStates[position]){
                holder.binding.itemReelsFollowTv.text = "팔로우"
                followStates[position] = false
            }else {
                holder.binding.itemReelsFollowTv.text = "팔로잉"
                followStates[position] = true
            }
        }

        holder.binding.itemReelsLikeLayout.setOnClickListener{  // 좋아요 버튼
            if(likeStates[position]){
                // 좋아요 취소
                holder.binding.itemReelsLikeIv.visibility = View.VISIBLE
                holder.binding.itemReelsLikeRedIv.visibility = View.GONE
                likeStates[position] = false
                //Log.d("like", "좋아요 취소됨 : " + likeStates[position])
            }else {
                // 좋아요 눌림
                holder.binding.itemReelsLikeIv.visibility = View.GONE
                holder.binding.itemReelsLikeRedIv.visibility = View.VISIBLE
                likeStates[position] = true
                //Log.d("like", "좋아요 눌림 : " + likeStates[position])
            }
        }
    }

    //item view 객체를 담고 있는 viewHolder
    inner class ViewHolder(val binding: ItemReelsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            // 글자 자동 스크롤
            val marqueeTextView: TextView = binding.itemReelsSongInfoTv
            marqueeTextView.isSelected = true

            binding.itemReelsPostIv.setImageResource(reelsData[position].imageResourceId) // 릴스 이미지
            binding.itemReelsProfileIv.setImageResource(profileImgData[position].imageResourceId)   // 프로필 이미지
            binding.itemReelsNameTv.text = nameData[position]   // 유저명
            binding.itemReelsContentsTv.text = contentsData[position]   // 본문
            binding.itemReelsLikeNumTv.text = likeData[position]    // 좋아요 수
            binding.itemReelsCommentNumTv.text = commentData[position]  // 댓글 수
            binding.itemReelsShareNumTv.text = shareData[position]  // 공유 수
            binding.itemReelsSongInfoTv.text = songInfo[position]   // 노래 제목
        }
    }
}