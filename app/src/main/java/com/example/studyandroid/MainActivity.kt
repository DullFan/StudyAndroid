package com.example.studyandroid

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyandroid.adapter.BaseRvAdapter
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityMainBinding
import com.example.studyandroid.databinding.ItemMainRvBinding
import com.example.studyandroid.study.alerter.AlerterActivity
import com.example.studyandroid.study.bnvl.BottomNavigationViewOrLottieActivity
import com.example.studyandroid.study.coordinator_layout.CoordinatorLayoutActivity
import com.example.studyandroid.study.coroutine.CoroutineActivity
import com.example.studyandroid.study.customizeview.CustomizeViewActivity
import com.example.studyandroid.study.databinding_and_rv.DataBindingAndRvActivity
import com.example.studyandroid.study.download.DownloadActivity
import com.example.studyandroid.study.leancloud.LeanCLoudLogIn
import com.example.studyandroid.study.motionlayout.MotionLayoutActivity
import com.example.studyandroid.study.scratch.ScratchActivity
import com.example.studyandroid.study.searchlight.SearchlightActivity
import com.example.studyandroid.study.fast.FastActivity
import com.example.studyandroid.study.fingerprint.Fingerprint
import com.example.studyandroid.study.frameworktest.mvc.controller.MvcActivity
import com.example.studyandroid.study.frameworktest.mvp.view.MvpActivity
import com.example.studyandroid.study.frameworktest.mvvm.view.MvvmActivity
import com.example.studyandroid.study.loadsir.LoadSirActivity
import com.example.studyandroid.study.shared.SharedElementsActivity
import com.example.studyandroid.study.skinning.Skinning
import com.example.studyandroid.study.smartrefreshlayout.SmartRefreshLayoutActivity
import com.example.studyandroid.study.xutils.XUtilsActivity


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
                "探照灯",
                "快捷方式",
                "指纹识别",
                "换肤",
                "下载APK",
                "自定义View",
                "MVC",
                "MVP",
                "MVVM",
                "BottomNavigationView + Lottie",
                "共享动画",
                "协程 + Flow",
                "DataBinding + RecyclerView",
                "CoordinatorLayout的使用",
                "SmartRefreshLayout的使用",
                "LoadSir的使用",
                "Alerter的使用",
                "手写XUtils",
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
                            5 -> startA(Fingerprint::class.java, data)
                            6 -> startA(Skinning::class.java, data)
                            7 -> startA(DownloadActivity::class.java, data)
                            8 -> startA(CustomizeViewActivity::class.java, data)
                            9 -> startA(MvcActivity::class.java, data)
                            10 -> startA(MvpActivity::class.java, data)
                            11 -> startA(MvvmActivity::class.java, data)
                            12 -> startA(BottomNavigationViewOrLottieActivity::class.java, data)
                            13 -> startA(SharedElementsActivity::class.java, data)
                            14 -> startA(CoroutineActivity::class.java, data)
                            15 -> startA(DataBindingAndRvActivity::class.java, data)
                            16 -> startA(CoordinatorLayoutActivity::class.java, data)
                            17 -> startA(SmartRefreshLayoutActivity::class.java,data)
                            18 -> startA(LoadSirActivity::class.java,data)
                            19 -> startA(AlerterActivity::class.java,data)
                            20 -> startA(XUtilsActivity::class.java,data)
                        }
                    }
                }
            }
        }
    }
}