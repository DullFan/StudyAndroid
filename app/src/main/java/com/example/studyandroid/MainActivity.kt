package com.example.studyandroid

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyandroid.adapter.BaseRvAdapter
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityMainBinding
import com.example.studyandroid.databinding.ItemMainRvBinding
import com.example.studyandroid.study.toolbar.ToolbarActivity
import com.example.studyandroid.study.leancloud.LeanCLoudLogIn
import com.example.studyandroid.study.motionlayout.MotionLayoutActivity
import com.example.studyandroid.study.scratch.ScratchActivity
import com.example.studyandroid.study.searchlight.SearchlightActivity
import com.example.studyandroid.study.fast.FastActivity
import com.example.studyandroid.study.fingerprint.Fingerprint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var viewBind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        val listOf =
            listOf(
                "刮刮乐",
                "LeanCloud的使用",
                "MotionLayout的使用",
                "自定义View",
                "快捷方式",
                "应用栏",
                "指纹识别",
            )

        viewBind.mainRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = object : BaseRvAdapter<String>(R.layout.item_main_rv, listOf) {
                override fun onBind(rvDataBinding: ViewDataBinding, data: String, position: Int) {
                    rvDataBinding as ItemMainRvBinding
                    rvDataBinding.itemMainRvButton.text = data
                    rvDataBinding.itemMainRvButton.setOnClickListener {
                        when (position) {
                            0 -> startA(ScratchActivity::class.java, data)
                            1 -> startA(LeanCLoudLogIn::class.java, data)
                            2 -> startA(MotionLayoutActivity::class.java, data)
                            3 -> startA(SearchlightActivity::class.java, data)
                            4 -> startA(FastActivity::class.java, data)
                            5 -> startA(ToolbarActivity::class.java, data)
                            6 -> startA(Fingerprint::class.java, data)
                        }
                    }
                }
            }
        }
    }
}