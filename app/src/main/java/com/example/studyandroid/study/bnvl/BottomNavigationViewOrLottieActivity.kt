package com.example.studyandroid.study.bnvl

import LottieAnimation
import android.app.Service
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.forEach
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityBottomNavigationViewOrLottieBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewOrLottieActivity : BaseActivity() {
    private var mPreClickPosition = 0

    private val navigationAnimationList = arrayListOf(
        LottieAnimation.LIVE,
        LottieAnimation.GAME,
        LottieAnimation.MESSAGE,
        LottieAnimation.MINE
    )

    private val navigationTitleList = arrayListOf(
        "直播",
        "派对",
        "消息",
        "我的"
    )


    lateinit var viewBind: ActivityBottomNavigationViewOrLottieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityBottomNavigationViewOrLottieBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        initBottomNavigationView()

    }

    private fun initBottomNavigationView() {
        viewBind.bottomNavBar.menu.apply {
            for (i in 0 until navigationTitleList.size) {
                add(Menu.NONE, i, Menu.NONE, navigationTitleList[i])
            }
            setLottieDrawable(getLottieAnimationList())
        }
        initListeners()
    }

    /**
     * 初始化监听事件
     */
    private fun initListeners() {
        viewBind.bottomNavBar.setOnNavigationItemSelectedListener {
            handleNavigationItem(it)
            return@setOnNavigationItemSelectedListener true
        }

        viewBind.bottomNavBar.setOnNavigationItemReselectedListener {
            handleNavigationItem(it)
        }

        //默认选中第一个
        viewBind.bottomNavBar.selectedItemId = 0

        //处理长按MenuItem提示TooltipText
        viewBind.bottomNavBar.menu.forEach {
            val menuItemView =
                viewBind.bottomNavBar.findViewById(it.itemId) as BottomNavigationItemView
            menuItemView.setOnLongClickListener {
                true
            }
        }

    }

    private fun handleNavigationItem(item: MenuItem) {
        handlePlayLottieAnimation(item)
        //在这里可以做一些ViewPager之间的逻辑
        mPreClickPosition = item.itemId
    }

    private fun handlePlayLottieAnimation(item: MenuItem) {
        val currentIcon = item.icon as? LottieDrawable
        //开启动画
        currentIcon?.apply {
            playAnimation()
        }

        // 处理 tab 切换，icon 对应调整，就是将其他设置为灰色，选中的设置选中状态
        if (item.itemId != mPreClickPosition) {
            startDeviceVibrate(this)
            viewBind.bottomNavBar.menu.findItem(mPreClickPosition).icon =
                getLottieDrawable(
                    getLottieAnimationList()[mPreClickPosition],
                    viewBind.bottomNavBar
                )
        }

    }

    private fun Menu.setLottieDrawable(lottieAnimationList: ArrayList<LottieAnimation>) {
        for (i in 0 until navigationAnimationList.size) {
            val item = findItem(i)
            //转变为lottieDrawable
            item.icon = getLottieDrawable(lottieAnimationList[i], viewBind.bottomNavBar)
        }
    }

    private fun getLottieDrawable(
        animation: LottieAnimation,
        bottomNavigationView: BottomNavigationView
    ): LottieDrawable {
        return LottieDrawable().apply {
            // 加载lottie 数据
            val result = LottieCompositionFactory.fromAssetSync(
                bottomNavigationView.context.applicationContext, animation.value
            )
            //将一个Drawable.Callback对象绑定到这个Drawable上。对于想要支持动画的drawable的客户端来说是必须的。
            callback = bottomNavigationView
            // 设置动画
            composition = result.value
        }
    }


    /**
     * 获取不同模式下 Lottie json 文件
     */
    private fun getLottieAnimationList(): ArrayList<LottieAnimation> {
        return navigationAnimationList
    }

    /**
     * 开启震动
     */
    fun startDeviceVibrate(context: Context) {
        val vibrator = context.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        // 判断当前设备是否有震动器
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // long milliseconds：震动毫秒数, int amplitude：震动强度，该值必须介于 1 ～ 255 之间，或者 DEFAULT_AMPLITUDE
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        }
    }

}