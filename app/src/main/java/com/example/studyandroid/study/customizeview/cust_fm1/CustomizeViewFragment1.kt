package com.example.studyandroid.study.customizeview.cust_fm1

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseFragment
import com.example.studyandroid.databinding.FragmentCustomizeView1Binding

class CustomizeViewFragment1 : BaseFragment() {
    lateinit var viewBinding: FragmentCustomizeView1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCustomizeView1Binding.inflate(inflater,container,false)
        viewBinding.customizeView1Button1.setOnClickListener {
            setAnimation(Direction.LEFT_TO_RIGHT)
        }

        viewBinding.customizeView1Button2.setOnClickListener {
            setAnimation(Direction.RIGHT_TO_LEF)
        }

        return viewBinding.root
    }
    fun setAnimation(dir: Direction) {
        viewBinding.customizeView1Ui.mDirection = dir
        val animator: ValueAnimator = ObjectAnimator.ofFloat(0f, 1f)
        animator.setDuration(3000)
        animator.addUpdateListener {
            val num = it.animatedValue as Float
            viewBinding.customizeView1Ui.mCurrentProgress = num
        }
        animator.start()
    }


}