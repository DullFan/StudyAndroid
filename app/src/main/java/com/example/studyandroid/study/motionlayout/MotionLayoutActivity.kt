package com.example.studyandroid.study.motionlayout

import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import cn.leancloud.codec.Base64Decoder.main
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityMotionLayoutBinding

class MotionLayoutActivity : BaseActivity() {
    lateinit var viewBind: ActivityMotionLayoutBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMotionLayoutBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.motion_fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration
            .Builder(setOf(R.id.motionFragment1, R.id.motionFragment2, R.id.motionFragment3,R.id.motionFragment4))
            .setOpenableLayout(viewBind.motionDrawer)
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(viewBind.motionNavigationView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}