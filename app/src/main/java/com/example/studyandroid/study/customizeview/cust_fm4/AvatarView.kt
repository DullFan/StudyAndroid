package com.example.studyandroid.study.customizeview.cust_fm4

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.R
import com.example.studyandroid.utils.px


private val IMAGE_WIDTH = 200f.px
private val IMAGE_PADDING = 20f.px
private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

class AvatarView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(
        IMAGE_PADDING,
        IMAGE_PADDING,
        IMAGE_PADDING + IMAGE_WIDTH,
        IMAGE_PADDING + IMAGE_WIDTH
    )


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //保存某个图层状态,这个很耗内存,所以最好是保存某个区域
        val saveLayer = canvas?.saveLayer(bounds, null)
        canvas?.drawOval(
            IMAGE_PADDING,
            IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH,
            paint
        )
        paint.xfermode = XFERMODE
        canvas?.drawBitmap(getAvatar(IMAGE_WIDTH.toInt()), IMAGE_PADDING, IMAGE_PADDING, paint)
        //恢复之前的图层
        if (saveLayer != null) {
            canvas?.restoreToCount(saveLayer)
        }
    }


    //统一图片大小，避免出现一高一地的情况
    fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        //如果inJustDecoedBounds设置为true的话，解码bitmap时可以只返回其高、宽和Mime类型，而不必为其申请内存，从而节省了内存空间。
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.rv_img10, options)
        options.inJustDecodeBounds = false
        //原来是多大
        options.inDensity = options.outWidth
        //目标是多大
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.rv_img10, options)
    }

}