package com.example.studyandroid.study.scratch

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.R
import android.view.MotionEvent
import com.example.studyandroid.utils.showLog
/**
 * 使用蒙版当作背景，带有监听器器，刮刮到一半就会清除所有
 */
class ScratchView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    //阴影层
    private val mPath = Path()

    private var mPaintSize = 50f
        set(value) {
            field = value
        }


    //绘制Path的画笔
    private val mPaint = Paint().apply {
        //抗锯齿
        isAntiAlias = true
        //防抖动
        isDither = true
        //设置为圆角
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        //只填充不描边
        style = Paint.Style.FILL
        //画笔宽度
        strokeWidth = mPaintSize
    }

    //记录用户每次开始坐标值
    private var mLastX: Float = 0.0f
    private var mLastY: Float = 0.0f

    //奖品图片
    private var mBgBm: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.rv_img10)

    private lateinit var mCanvas: Canvas
    private lateinit var mGrayBm: Bitmap

    //初始化接口变量
    var onScratchListener: OnScratchListener? = null
        set(value) {
            field = value
        }

    var mText: String = "一等奖"
        set(value) {
            field = value
        }
    private val mTextBound: Rect by lazy { Rect() }



    private val mBackPaint: Paint = Paint().apply {
        color = Color.RED;
        style = Paint.Style.FILL
        textSize = mPaintSize
        getTextBounds(mText, 0, mText.length, mTextBound)
    }

    //设置标志位，判断是否被清除
    private var flag: Boolean = true


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //创建位图
        mGrayBm = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
        //用指定的位图构造一个画布来绘制。
        mCanvas = Canvas(mGrayBm)

        //绘制阴影
        mCanvas.drawColor(Color.GRAY)

        mCanvas.drawRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            30f,
            30f,
            mPaint
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.style = Paint.Style.STROKE
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        mBackPaint.textAlign = Paint.Align.CENTER
        canvas?.drawText(
            mText,
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            mBackPaint
        )
        if (flag) {
            //绘制灰色蒙层
            canvas?.drawBitmap(mGrayBm, 0f, 0f, null)
        }
        mCanvas.drawPath(mPath, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            val x = event.x
            val y = event.y

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX = x
                    mLastY = y
                    //移动下一次操作的起点位置
                    mPath.moveTo(mLastX, mLastY)
                }
                MotionEvent.ACTION_MOVE -> {
                    //添加上一个点到当前点之间的直线到Path
                    mPath.lineTo(x, y)
                    mLastX = x
                    mLastY = y
                }
                MotionEvent.ACTION_UP -> {
                    if (flag) {
                        lateinit var pixels: IntArray
                        val w: Int = mGrayBm.width
                        val h: Int = mGrayBm.height
                        var wipeArea = 0f
                        val totalArea = (w * h).toFloat()
                        pixels = IntArray(w * h)
                        /**
                         * pixels      接收位图颜色值的数组
                         * offset      写入到pixels[]中的第一个像素索引值
                         * stride      pixels[]中的行间距个数值(必须大于等于位图宽度)。可以为负数
                         * x          　从位图中读取的第一个像素的x坐标值。
                         * y           从位图中读取的第一个像素的y坐标值
                         * width    　　从每一行中读取的像素宽度
                         * height 　　　读取的行数
                         */
                        mGrayBm.getPixels(pixels, 0, w, 0, 0, w, h)
                        for (i in 0 until w) {
                            for (j in 0 until h) {
                                val index = i + j * w
                                if (pixels[index] == 0) {
                                    wipeArea++
                                }
                            }
                        }
                        if (wipeArea > 0 && totalArea > 0) {
                            var percent = (wipeArea * 100 / totalArea).toInt()
                            showLog(percent)
                            if (percent in 35..99) {
                                onScratchListener?.onScratchListener(mText)
                                flag = false
                                postInvalidate()
                            }
                        }
                    }
                }
            }
        }
        //刷新UI
        invalidate()
        return true
    }

    //先定义接口
    interface OnScratchListener {
        fun onScratchListener(string: String)
    }
}