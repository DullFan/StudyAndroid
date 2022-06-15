package com.example.studyandroid.study.customizeview.cust_fm3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.utils.px
import kotlin.math.cos
import kotlin.math.sin


private val RADIUS = 150f.px

//圆角角度，合起来360°即可
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)

//颜色
private val COLORS = listOf(
    Color.parseColor("#C2185B"),
    Color.parseColor("#00ACC1"),
    Color.parseColor("#558B2F"),
    Color.parseColor("#5D4037"),
)

private val OFFSET_LENGTH = 20f.px


class PieView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var startAngle = 0f

        //循环集合，绘制饼图
        //withIndex():返回一个惰性 Iterable，它将原始数组的每个元素包装成一个 IndexedValue，其中包含该元素的索引和元素本身。
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == 2) {
                canvas?.save()
                canvas?.translate(
                    ((OFFSET_LENGTH * cos(Math.toRadians((startAngle + angle / 2f).toDouble()))).toFloat()),
                    (OFFSET_LENGTH * sin(Math.toRadians((startAngle + angle / 2f).toDouble()))).toFloat()
                )
            }
            //画弧
            canvas?.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                startAngle,
                angle,
                true,
                paint
            )
            startAngle += angle
            if (index == 2) {
                canvas?.restore()
            }
        }
    }


}