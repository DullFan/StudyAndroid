package com.example.studyandroid.study.coroutine

import android.os.Bundle
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.*

class CoroutineActivity : BaseActivity(), CoroutineScope by MainScope() {
    private val viewDataBinding by lazy {
        ActivityCoroutineBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewDataBinding.root)


    }
}