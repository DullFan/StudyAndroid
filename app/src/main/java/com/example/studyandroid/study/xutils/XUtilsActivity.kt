package com.example.studyandroid.study.xutils

import android.os.Bundle
import android.widget.TextView
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.study.xutils.annation.BindView
import com.example.studyandroid.study.xutils.annation.ContentView

@ContentView(R.layout.activity_xutils)
class XUtilsActivity : BaseActivity() {
    @BindView(R.id.xutils_text)
    lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectTool.inject(this)
        textView.setText("这个值我修改了")
    }
}