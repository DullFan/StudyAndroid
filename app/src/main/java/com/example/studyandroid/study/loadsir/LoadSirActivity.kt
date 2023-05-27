package com.example.studyandroid.study.loadsir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityLoadSirBinding
import com.example.studyandroid.study.loadsir.callback.AnimateCallback
import com.example.studyandroid.study.loadsir.callback.ErrorCallback
import com.example.studyandroid.utils.showLog
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

class LoadSirActivity : BaseActivity() {

    val viewDataBinding by lazy {
        ActivityLoadSirBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewDataBinding.root)
        val register = LoadSir.getDefault().register(this) {
            //重新加载
            showLog("重新加载")
        }
        //错误页面
        register.showCallback(ErrorCallback::class.java)
        //动画页面
        register.showCallback(AnimateCallback::class.java)
    }
}