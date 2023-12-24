package com.example.instagram.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.databinding.ItemHomePostImgBinding

class PostImageFragment : Fragment() {

    // 해당 Fragment 클래스에서 RecyclerView의 ViewPager 안에 각각의 이미지를 가져와 표시하는데 사용된다
    // 각 이미지는 독립된 Fragment로 표시되기 때문에 코드의 재사용성이 높아진다는 장점이 있다

    private var imageResourceId: Int = 0
    private lateinit var binding : ItemHomePostImgBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomePostImgBinding.inflate(inflater, container, false)

        val imageView = binding.itemHomePostImgIv
        imageView.setImageResource(imageResourceId)

        return binding.root
    }

    // Companion object를 사용하여 Fragment 인스턴스 생성
    companion object {
        fun newInstance(imageResourceId: Int): PostImageFragment {
            val fragment = PostImageFragment()
            val args = Bundle()

            // 이미지 리소스 ID를 Bundle에 담아 인스턴스에 전달
            args.putInt("imageResourceId", imageResourceId)
            fragment.arguments = args

            // 생성된 Fragment 인스턴스 반환
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bundle에서 이미지 리소스 ID를 가져와 할당
        arguments?.let {
            imageResourceId = it.getInt("imageResourceId")
        }
    }
}