package com.example.studyandroid.study.fast

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityFastBinding
import com.example.studyandroid.study.motionlayout.MotionLayoutActivity

class FastActivity : BaseActivity() {
    lateinit var viewBind: ActivityFastBinding

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityFastBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        viewBind.fastButton1.setOnClickListener {
            onClickshortcutsAdd()
        }

        viewBind.fastButton2.setOnClickListener {
            onClickshortcutsDel()
        }


    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun onClickshortcutsDel() {
        val shortcutManager = getSystemService(ShortcutManager::class.java) as ShortcutManager
        //根据Id移除动态快捷方式
        shortcutManager.removeDynamicShortcuts(listOf("demo"))
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun onClickshortcutsAdd() {
        //按类将句柄返回给系统级服务
        val shortcutManager = getSystemService(ShortcutManager::class.java) as ShortcutManager
        val intent = Intent(this, MotionLayoutActivity::class.java)
        //显示给用户看
        intent.action = Intent.ACTION_VIEW
        val shortcut = ShortcutInfo.Builder(this, "demo")
            //设置图标
            .setIcon(Icon.createWithResource(this, R.drawable.rv_img10))
            //短标题
            .setShortLabel("通知渠道")
            //长标题
            .setLongLabel("打开MotionLayout")
            //跳转的位置
            .setIntent(intent)
            .build()
        //添加到动态快捷方式列表
        shortcutManager.addDynamicShortcuts(listOf(shortcut))
    }
}