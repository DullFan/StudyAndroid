package com.example.studyandroid.study.leancloud

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import cn.leancloud.LCUser
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityLeanCloudLogInBinding
import com.example.studyandroid.utils.showLog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * LeanCLoud注册页
 */
class LeanCLoudLogIn : BaseActivity() {
    lateinit var viewBind: ActivityLeanCloudLogInBinding

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val t1 = viewBind.loginEd1.text.toString().isNotEmpty()
            val t2 = viewBind.loginEd2.text.toString().isNotEmpty()
            viewBind.loginButton1.isEnabled = t1 and t2
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityLeanCloudLogInBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        viewBind.loginButton1.isEnabled = false
        viewBind.loginEd1.addTextChangedListener(watcher)
        viewBind.loginEd2.addTextChangedListener(watcher)
        viewBind.loginButton2.setOnClickListener {
            startA(LeanCLoudSignUp::class.java,"注册")
        }

        viewBind.loginButton1.setOnClickListener {
            val t1 = viewBind.loginEd1.text?.trim().toString()
            val t2 = viewBind.loginEd2.text?.trim().toString()
            LCUser.logIn(t1, t2).subscribe(object : Observer<LCUser> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: LCUser) {
                    startA(LeanCLoudHome::class.java,"主页")
                    finish()
                }

                override fun onError(e: Throwable) {
                    showLog(e)
                }

                override fun onComplete() {

                }
            })

        }


    }
}