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

        return viewBinding.root
    }



}