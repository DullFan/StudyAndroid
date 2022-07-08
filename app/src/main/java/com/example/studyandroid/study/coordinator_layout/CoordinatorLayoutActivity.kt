package com.example.studyandroid.study.coordinator_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityCoordinatorLayoutBinding

class CoordinatorLayoutActivity : BaseActivity() {
    val dataBinding by lazy {
        ActivityCoordinatorLayoutBinding.inflate(layoutInflater)
    }
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dataBinding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.coordinator_layout_fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration
            .Builder(
                setOf(
                    R.id.coordinatorLayoutFragment01,
                    R.id.coordinatorLayoutFragment02,
                    R.id.coordinatorLayoutFragment03,
                    R.id.coordinatorLayoutFragment04,
                    R.id.coordinatorLayoutFragment05,
                )
            )
            .setOpenableLayout(dataBinding.coordinatorLayoutDrawer)
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(
            dataBinding.coordinatorLayoutNavigationView,
            navController
        )
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}