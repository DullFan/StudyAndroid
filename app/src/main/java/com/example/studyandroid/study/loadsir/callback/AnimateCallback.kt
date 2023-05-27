package com.example.studyandroid.study.loadsir.callback

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.example.studyandroid.R
import com.kingja.loadsir.callback.Callback

class AnimateCallback : Callback() {
    lateinit var mContext: Context
    lateinit var mAnimateView: View


    override fun onCreateView(): Int {
        return R.layout.call_back_animate
    }

    override fun onAttach(context: Context, view: View) {
        mContext = context
        mAnimateView = view.findViewById(R.id.call_back_animate_view)
        val animation = RotateAnimation(
            0f,
            359f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        animation.duration = 1000
        animation.repeatCount = Int.MAX_VALUE
        animation.fillAfter = true
        animation.interpolator = LinearInterpolator()
        mAnimateView.startAnimation(animation)
        Toast.makeText(context.applicationContext,"开始动画了",Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        if(mAnimateView != null){
            mAnimateView.clearAnimation()
        }
        Toast.makeText(mContext.applicationContext,"动画停止了",Toast.LENGTH_SHORT)
    }
}