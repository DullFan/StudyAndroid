package com.example.studyandroid.study.alerter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityAlerterBinding
import com.tapadoo.alerter.Alerter

class AlerterActivity : BaseActivity() {

    private val viewDataBinding by lazy {
        ActivityAlerterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewDataBinding.root)

        viewDataBinding.alerterButton1.setOnClickListener {
            Alerter.create(this)
                .setTitle("标题")
                .setText("文本文本文本")
                .show()
        }


    }
}