package com.example.studyandroid.study.customizeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.studyandroid.R
import com.example.studyandroid.databinding.ActivityCustomizeViewBinding

class CustomizeViewActivity : AppCompatActivity() {
    private val viewBind by lazy {
        ActivityCustomizeViewBinding.inflate(layoutInflater)
    }
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBind.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.customize_fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration
            .Builder(setOf(R.id.customizeViewFragment1))
            .setOpenableLayout(viewBind.customizeDrawer)
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(viewBind.customizeNavigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}