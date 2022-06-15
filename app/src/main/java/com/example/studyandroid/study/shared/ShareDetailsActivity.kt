package com.example.studyandroid.study.shared

import android.content.Intent
import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.widget.Toast
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.utils.showLog
import kotlin.math.log

class ShareDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_details)
        //退出需要使用finishAfterTransition()
        //finishAfterTransition(); // 直接finish会没有这个效果



    }

    override fun onResume() {
        super.onResume()
        val stringExtra = intent.getStringExtra("title")
        if(stringExtra?.isNotEmpty() == true){
            supportActionBar?.title = stringExtra
        }
    }

}