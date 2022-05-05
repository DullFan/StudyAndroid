package com.example.studyandroid.study.customizeview.cust_fm1

import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.studyandroid.R

enum class Direction {
    LEFT_TO_RIGHT, RIGHT_TO_LEF
}

class CustomizeViewFragmentUi1(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {
    //改变的颜色
    var changeColor = 0

    //初始的颜色
    var originColor = 0

    //当前变色进度
    var mCurrentProgress = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    //实现不同朝向
    var mDirection: Direction = Direction.LEFT_TO_RIGHT


    //初始画笔
    private val mOriginPaint by lazy {
        getPaintStyle(originColor)
    }

    //改变的画笔
    private val mChangePaint by lazy {
        getPaintStyle(changeColor)
    }


    init {
        //获取自定义属性的值
        val typeArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.CustomizeViewFragmentUi1)
        changeColor = typeArray.getColor(
            R.styleable.CustomizeViewFragmentUi1_changeColor,
            textColors.defaultColor
        )
        originColor = typeArray.getColor(
            R.styleable.CustomizeViewFragmentUi1_originColor,
            textColors.defaultColor
        )
        //回收
        typeArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        val currentPoint = (mCurrentProgress * width).toInt()
        if(canvas != null){
            if (mDirection === Direction.LEFT_TO_RIGHT) {
                //绘制变色部分
                drawText(canvas, mChangePaint, 0, currentPoint)
                //绘制不变色部分
                drawText(canvas, mOriginPaint, currentPoint, width)
            } else {
                //绘制变色部分
                drawText(canvas, mChangePaint, width - currentPoint, width)
                //绘制不变色部分
                drawText(canvas, mOriginPaint, 0, width - currentPoint)
            }
        }
    }

    fun drawText(canvas: Canvas,paint: Paint,start:Int,end:Int){
        //保存当前画面
        canvas.save()
        //裁剪区域
        val rect = Rect(start, 0, end, height)
        //裁剪,只显示裁剪之后的区域
        canvas.clipRect(rect)
        //获取文字
        val text = text.toString()
        //判空
        if (TextUtils.isEmpty(text)) return
        //获取文字的区域
        val bounds = Rect()
        //会将文字区域给到bounds
        paint.getTextBounds(text, 0, text.length, bounds)
        //获取x坐标   居中效果
        val dx = width / 2 - bounds.width() / 2
        //获取基线 baseLine
        val fontMetrics = mChangePaint.fontMetricsInt
        val dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
        val baseLine = height / 2 + dy

        //绘制文字
        canvas.drawText(text, dx.toFloat(), baseLine.toFloat(), paint)
        //绘制之前保存的画面
        canvas.restore()
    }


    private fun getPaintStyle(colorStyle: Int): Paint {
        val paint = Paint().apply {
            color = colorStyle
            isAntiAlias = true
            isDither = true
            textSize = this@CustomizeViewFragmentUi1.textSize
        }
        return paint
    }
}