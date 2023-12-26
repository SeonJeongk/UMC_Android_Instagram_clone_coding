package com.example.instagram.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.data.entity.ImageItem
import com.example.instagram.data.entity.User
import com.example.instagram.databinding.ItemHomePostBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import me.relex.circleindicator.CircleIndicator3

class PostRVAdapter(private val uid: String?) : RecyclerView.Adapter<PostRVAdapter.ViewHolder>() {

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
        Log.d("호출 확인", "onCreateViewHolder")

        return ViewHolder(binding)  //item view 객체 return
    }

    override fun getItemCount(): Int = profileImgData.size

    override fun onBindViewHolder(holder: PostRVAdapter.ViewHolder, position: Int) {
        holder.bind(position)
        Log.d("호출 확인", "onBindViewHolder")
        if (uid != null) {
            val db = FirebaseFirestore.getInstance()
            val documentRef = db.collection("clients").document(uid!!)

            documentRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    val user: User? = documentSnapshot.toUser()

                    if (!user?.profile_img.isNullOrEmpty()) {
                        Glide.with(holder.itemView.context)
                            .load(user?.profile_img)
                            .circleCrop()
                            .into(holder.binding.itemHomePostCommentProfileIv)
                    } else {
                        // 기본 이미지 또는 에러 처리 등을 수행하도록 원하는 로직을 추가하세요.
                        holder.binding.itemHomePostCommentProfileIv.setImageResource(R.drawable.my_story_blank)
                    }
                }
                .addOnFailureListener { exception ->
                    // 오류 처리
                    Log.e("Firestore", "Error getting document: ", exception)
                }
        } else {
            Log.d("Firestore - Post", "Invalid uid: $uid")
            holder.binding.itemHomePostCommentProfileIv.setImageResource(R.drawable.my_story_blank)
        }
    }

    // MyFragment 클래스 내부 또는 동일한 파일에 추가
    fun DocumentSnapshot.toUser(): User? {
        return try {
            Log.d("호출 확인", "DocumentSnapshot")
            val uid = getString("uid") ?: ""
            val username = getString("username") ?: ""
            val name = getString("name") ?: ""
            val email = getString("email") ?: ""
            val profile_img = getString("profile_img") ?: ""
            val profile_info = getString("profile_info") ?: ""

            User(email, name, profile_img, profile_info, uid, username)
        } catch (e: Exception) {
            null // 변환에 실패한 경우 null 반환 또는 예외 처리를 수행할 수 있습니다.
        }
    }

    //item view 객체를 담고 있는 viewHolder
    inner class ViewHolder(val binding: ItemHomePostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            Log.d("호출 확인", "ViewHolder")
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
