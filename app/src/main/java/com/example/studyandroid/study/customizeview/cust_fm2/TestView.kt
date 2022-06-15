package com.example.studyandroid.study.customizeview.cust_fm2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.utils.px
import kotlin.math.cos
import kotlin.math.sin

private const val OPEN_ANGLE = 120f
private val DASH_WIDTH = 2f.px
private val DASH_LENGTH = 10f.px
private val RADIUS = 150f.px
private val LENGTH = 120f.px
private val MARK = 10

class TestView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dash = Path()
    private lateinit var pathDashPathEffect: PathDashPathEffect;

    init {
        paint.strokeWidth = 3f.px
        paint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + OPEN_ANGLE / 2f,
            360 - OPEN_ANGLE
        )

        val pathMeasure = PathMeasure(path, false)

        //写完跑起来发现有20个间隔，21个刻度，所以需要将刻度减1，才能显示正常
        pathDashPathEffect =
            PathDashPathEffect(
                dash,
                (pathMeasure.length - DASH_WIDTH) / 20f,
                0f,
                PathDashPathEffect.Style.ROTATE
            )
    }

    override fun onDraw(canvas: Canvas?) {
        //先绘制弧形
        canvas?.drawPath(path, paint)

        //在来设置刻度效果
        //设置虚线效果
        // shape：显示的形状
        // advance：每间隔多少画一个
        // phase：前置量，就是第一个刻度要不要空多少距离
        // style：使用什么样式
        paint.pathEffect = pathDashPathEffect
        canvas?.drawPath(path, paint)
        //在清除效果
        paint.pathEffect = null

        canvas?.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + LENGTH * cos(markToRadians(MARK).toFloat()),
            height / 2f + LENGTH * sin(markToRadians(MARK).toFloat()),
            paint
        )
    }

    //三角函数q算
    //mark:表示当前指针指向的位置
    private fun markToRadians(mark:Int) =
        (Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE) / 20f * mark).toDouble()))
}