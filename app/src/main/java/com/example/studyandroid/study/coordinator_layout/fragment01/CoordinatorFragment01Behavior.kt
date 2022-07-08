package com.example.studyandroid.study.coordinator_layout.fragment01

import android.content.Context
import android.util.AttributeSet
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.studyandroid.utils.showLog

class CoordinatorFragment01Behavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    //判断child是否依赖dependency
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is CoordinatorFragment01TextView
    }

    //子View依赖的View改变时做出响应，当依赖视图在标准布局流之外的大小或者位置发生变化，此方法被调用。
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        //获取child的坐标
        var childX = dependency.x
        var childY = 0

        //dependency就是CoordinatorFragment01TextView
        showLog(dependency)
        //child就是ImageView
        showLog(child)

        //如果是TextView就放置头部
        if(child is TextView){
            childY = dependency.top - child.height
        } else {
            childY = dependency.bottom
        }

        child.x = childX
        child.y = childY.toFloat()

        return true
    }

}