package com.example.studyandroid.study.customizeview.cust_fm1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.studyandroid.R
import com.example.studyandroid.utils.px

private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 20.px
private val RADIUS = 150.px



class CustomizeViewFragmentUi1(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20.px
        //设置字体
        typeface = ResourcesCompat.getFont(context, R.font.text_text)
        //是否加粗文字
        isFakeBoldText = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制环
        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        //绘制进度条
        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS,
            height / 2f + RADIUS, -90f, 225f, false, paint
        )

        paint.style = Paint.Style.FILL
        //绘制文字
        canvas.drawText("1000KM",width / 2f, height / 2f ,paint)
    }
}