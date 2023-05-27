package com.example.studyandroid.study.customizeview.cust_fm5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.study.customizeview.utils.px

val RADIUS = 100f.px

class CustFm5View(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CCW)
        path.addRect(
            width / 2f - RADIUS,
            height / 2f,
            width / 2f + RADIUS,
            height / 2f + 2 * RADIUS,
            Path.Direction.CCW
        )
    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawPath(path, paint)
    }
}