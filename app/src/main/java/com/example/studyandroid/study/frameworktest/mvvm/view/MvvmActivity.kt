package com.example.studyandroid.study.frameworktest.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityMvvmBinding
import com.example.studyandroid.study.frameworktest.mvvm.viewmodel.MvvmViewModel

class MvvmActivity : BaseActivity() {
    private val viewModel: MvvmViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            MvvmViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                viewModel.onResetSelected()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_jingziqi, menu)
        return super.onCreateOptionsMenu(menu)
    }
}