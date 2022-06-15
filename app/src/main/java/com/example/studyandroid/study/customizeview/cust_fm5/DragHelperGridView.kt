package com.example.studyandroid.study.customizeview.cust_fm5

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.children
import com.example.studyandroid.R
import com.example.studyandroid.study.customizeview.cust_fm4.AvatarView


class DragHelperGridView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var dragStarter = OnLongClickListener { v ->
        val imageData = ClipData.newPlainText("name",v.contentDescription)
        //Android系统提供了一个拖拽放下功能，可以实现同一个APP中不同Activity，不同Fragment甚至跨APP的控件拖拽功能。
        //View.startDragAndDrop方法会传递一个DragShadowBuilder对象给系统，
        // 系统稍后会调用DragShadowBuilder#onDrawShadow(Canvas)方法。
        // 一旦系统得到DragShadowBuilder对象那么它就开始执行拖动和放下操作，
        // 同时会发送事件给当前所有Visible的View对象。接收事件的组件，注册OnDragListener对象来接收拖动事件。
        ViewCompat.startDragAndDrop(v,imageData,DragShadowBuilder(v),null,0)
    }

    private var dragListener:OnDragListener = CollectListener()

    //当View中所有的子控件均被映射成xml后触发
    override fun onFinishInflate() {
        super.onFinishInflate()
        val avatarView:ImageView = findViewById(R.id.avatarView)
        val logoView:ImageView = findViewById(R.id.logoView)
        val collectorLayout:LinearLayout = findViewById(R.id.collectorLayout)
        avatarView.setOnLongClickListener(dragStarter)
        logoView.setOnLongClickListener(dragStarter)
        collectorLayout.setOnDragListener(dragListener)
    }


    inner class CollectListener : OnDragListener {
        override fun onDrag(v: View?, event: DragEvent?): Boolean {
            //event?.action:检查此事件的操作值。
            when(event?.action){
                DragEvent.ACTION_DROP -> if (v is LinearLayout){
                    val textView = TextView(context)
                    textView.textSize = 16f
                    textView.text = event.clipData.getItemAt(0).text
                    v.addView(textView)
                }
            }
            return true
        }
    }
}