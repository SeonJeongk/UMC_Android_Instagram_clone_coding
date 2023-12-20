package com.example.instagram.ui.search

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.data.ImageItem
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.databinding.FragmentReelsBinding
import com.example.instagram.databinding.FragmentSearchBinding
import com.example.instagram.ui.main.MainActivity

class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        clickListener()

        val data = arrayOf(
            ImageItem(R.drawable.post_1),
            ImageItem(R.drawable.post_2),
            ImageItem(R.drawable.post_3),
            ImageItem(R.drawable.post_4),
            ImageItem(R.drawable.post_5),
            ImageItem(R.drawable.post_6),
            ImageItem(R.drawable.post_7),
            ImageItem(R.drawable.post_8),
            ImageItem(R.drawable.post_9),
            ImageItem(R.drawable.post_10),
            ImageItem(R.drawable.post_11),
            ImageItem(R.drawable.post_12),
            ImageItem(R.drawable.post_13),
            ImageItem(R.drawable.post_14),
            ImageItem(R.drawable.post_15),
            ImageItem(R.drawable.post_8),
            ImageItem(R.drawable.post_9),
            ImageItem(R.drawable.post_10),
            ImageItem(R.drawable.post_11),
            ImageItem(R.drawable.post_12),
            ImageItem(R.drawable.post_13),
            ImageItem(R.drawable.post_14),
            ImageItem(R.drawable.post_15),
        )

        // 어댑터 설정
        val adapter = ImageAdapter(requireContext(), data)
        binding.searchPostGrid.adapter = adapter

        return binding.root
    }

    private fun clickListener() {
        binding.searchEtBtn.setOnClickListener {
            Log.d("SearchFragment", "EditText Clicked")
            changeFragmentToDetail()
        }

        binding.searchPostGrid.setOnItemClickListener { _, _, position, _ ->
            changeFragmentToTab()
        }
    }

    private fun changeFragmentToDetail() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, SearchDatailFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }

    private fun changeFragmentToTab() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, SearchTabFragment()).addToBackStack(tag)
            .commitAllowingStateLoss()
    }

    class ImageAdapter(private val context: Context, private val data: Array<ImageItem>) : BaseAdapter() {

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View

            if (convertView == null) {
                // 커스텀 그리드뷰 아이템 레이아웃을 사용하여 뷰를 생성
                view = inflater.inflate(R.layout.item_grid, null)
                val imageView = view.findViewById<ImageView>(R.id.gridItemImageView)

                // 데이터 설정
                val item = getItem(position) as ImageItem
                imageView.setImageResource(item.imageResourceId)
            } else {
                view = convertView
            }

            return view
        }
    }
}