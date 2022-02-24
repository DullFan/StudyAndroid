package com.example.studyandroid.study.scratch

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.R
import android.view.MotionEvent
import com.example.studyandroid.utils.showLog

/**
 * 使用自己定义的图片设置刮刮乐
 */
class ScratchViewTwo(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    //手刮动的path，过程
    private val mPath: Path by lazy { Path() }

    //绘制mPath的画笔
    private val mOutterPaint: Paint = Paint().apply {
        color = Color.RED;
        isAntiAlias = true;
        isDither = true;
        strokeJoin = Paint.Join.ROUND;//设置圆角
        strokeCap = Paint.Cap.ROUND;
        style = Paint.Style.FILL;
        strokeWidth = 60f;//设置画笔宽度
    }

    private lateinit var mCanvas: Canvas
    private lateinit var mBitmap: Bitmap

    //记录用户path每次的开始坐标值
    private var mLastX: Float = 0f
    private var mLastY: Float = 0f

    //图片遮罩，就是手刮动，要擦掉的那张图
    private val mOutterBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.rv_img10)
    }

    //刮奖文本信息
    private val mText: String = "一等奖"
    private val mTextBound: Rect by lazy { Rect() }

    //刮奖信息的画笔
    private val mBackPaint: Paint = Paint().apply {
        color = Color.RED;
        style = Paint.Style.FILL
        textSize = 60f;
        //获得当前画笔绘制文本的宽和高
        getTextBounds(mText, 0, mText.length, mTextBound)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取控件的宽高
        val width = measuredWidth
        val height = measuredHeight
        //初始化Bitmap
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
        //用mOutterPaint画圆角矩形
        mCanvas.drawRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            30f,
            30f,
            mOutterPaint
        )
        //在刚刚画的圆角矩形上面再画一个bitmap图片，让图片大小和圆角矩形大小相关联
        mCanvas.drawBitmap(mOutterBitmap, null, Rect(0, 0, width, height), null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mOutterPaint.style = Paint.Style.STROKE
        //Mode.DST_OUT改模式就类似橡皮檫，这个属性设置是关键
        mOutterPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        //把获奖信息放在正中间
        canvas?.drawText(
            mText,
            ((width - mTextBound.width()) / 2).toFloat(),
            (height / 2 - mTextBound.height() / 2).toFloat(),
            mBackPaint
        )
        mCanvas.drawPath(mPath, mOutterPaint);
        canvas?.drawBitmap(mBitmap, 0f, 0f, null);
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event != null){
            val x = event.x.toInt()
            val y = event.y.toInt()

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    showLog("ACTION_DOWN")
                    //记录按下的时候的X和Y值，以便于之后移动的时候绘制
                    mLastX = x.toFloat()
                    mLastY = y.toFloat()
                    mPath.moveTo(mLastX, mLastY)
                }
                MotionEvent.ACTION_MOVE -> {
                    showLog("ACTION_MOVE")
                    //拿到用户移动的X绝对值，Y轴绝对值
                    val dx = Math.abs(x - mLastX).toInt()
                    val dy = Math.abs(y - mLastY).toInt()
                    //用户滑动超过3像素才会改变，这个可以不做，做只是为了避免很频繁的相应而已。
                    mPath.lineTo(x.toFloat(), y.toFloat())
                    mLastX = x.toFloat()
                    mLastY = y.toFloat()
                }
            }
        }

        invalidate() //刷新UI
        return true
    }

}