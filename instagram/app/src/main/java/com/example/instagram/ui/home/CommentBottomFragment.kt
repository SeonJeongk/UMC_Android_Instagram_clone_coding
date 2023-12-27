package com.example.instagram.ui.home

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.example.instagram.databinding.FragmentCommentSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CommentBottomFragment:BottomSheetDialogFragment() {
    private lateinit var binding:FragmentCommentSheetBinding
    private var img : String =""
    private var username : String =""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentSheetBinding.inflate(inflater, container, false)

        img = arguments?.getString("img", "").toString()
        username = arguments?.getString("username", "").toString()

        visibleBtn()
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {  //키보드 크기만큼 스크롤
        // windowSoftInputMode 속성을 설정합니다.
        return BottomSheetDialog(requireContext(), theme).apply {
            setOnShowListener {
                val dialog = it as BottomSheetDialog
                val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                Glide.with(requireContext())
                    .load(img)
                    .circleCrop()
                    .into(binding.commentWriteLayoutProfileImg)
                binding.commentWriteLayoutEt.hint = username + "로 댓글 달기..."
            }
        }
    }
    private fun visibleBtn() {
        binding.commentWriteLayoutEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.isNotEmpty()) {
                    binding.commentWriteBtn.visibility = View.VISIBLE
                } else {
                    binding.commentWriteBtn.visibility = View.GONE
                }
            }
        })
        }
    }
