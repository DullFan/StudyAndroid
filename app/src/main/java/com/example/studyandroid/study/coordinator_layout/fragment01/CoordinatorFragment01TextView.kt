package com.example.studyandroid.study.coordinator_layout.fragment01

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat

class CoordinatorFragment01TextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {
    private var mLastX = 0f
    private var mLastY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastY = event.y
                mLastX = event.x
            }

            MotionEvent.ACTION_MOVE -> {
                val moveX = event.x - mLastX
                val moveY = event.y - mLastY

                //移动视图
                ViewCompat.offsetLeftAndRight(this, moveX.toInt())
                ViewCompat.offsetTopAndBottom(this, moveY.toInt())
                mLastX = event.x
                mLastY = event.y
            }

            MotionEvent.ACTION_UP -> {
                mLastY = event.y
                mLastX = event.x
            }
        }
        return true
    }


}