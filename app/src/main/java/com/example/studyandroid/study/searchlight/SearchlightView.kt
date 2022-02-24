package com.example.studyandroid.study.searchlight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.studyandroid.R
import kotlin.random.Random

class SearchlightView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    //获取图片
    private val faceBitmap =
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_android_24)?.toBitmap(300, 300)

    private var faceX = 0f
    private var faceY = 0f

    //路径
    private val path = Path()

    //路径画笔
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context,R.color.black)
    }

    private fun randomPosition(){
        faceX = Random.nextInt(width - 300).toFloat()
        faceY = Random.nextInt(height - 300).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制图标
        canvas?.apply {
            faceBitmap?.let { drawBitmap(it,faceX,faceY,null) }
            drawPath(path,paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when(action){
                MotionEvent.ACTION_DOWN ->{
                    randomPosition()
                }

                MotionEvent.ACTION_MOVE ->{
                    path.reset()
                    path.addRect(0f,0f,width.toFloat(),height.toFloat(),Path.Direction.CW)
                    path.addCircle(x,y,300f,Path.Direction.CCW)
                }
                MotionEvent.ACTION_DOWN->{
                    //重置
                    path.reset()
                }
            }
            invalidate()
        }
        performClick()
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}