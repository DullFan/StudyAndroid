package com.example.studyandroid.study.leancloud

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import cn.leancloud.LCUser
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityLeanCloudSignUpBinding
import com.example.studyandroid.utils.showLog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LeanCLoudSignUp : BaseActivity() {
    lateinit var viewBind: ActivityLeanCloudSignUpBinding
    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val t1 = viewBind.singUpEd1.text.toString().isNotEmpty()
            val t2 = viewBind.singUpEd2.text.toString().isNotEmpty()
            viewBind.singUpButton.isEnabled = t1 and t2
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityLeanCloudSignUpBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        viewBind.singUpButton.isEnabled = false
        viewBind.singUpEd1.addTextChangedListener(watcher)
        viewBind.singUpEd2.addTextChangedListener(watcher)

        viewBind.singUpButton.setOnClickListener {
            val t1 = viewBind.singUpEd1.text?.trim().toString()
            val t2 = viewBind.singUpEd2.text?.trim().toString()
            //注册
            LCUser().apply {
                username = t1
                password = t2
                signUpInBackground().subscribe(object : Observer<LCUser> {
                    //订阅成功
                    override fun onSubscribe(d: Disposable) {

                    }

                    //请求成功
                    override fun onNext(t: LCUser) {
                        showLog(t)
                        LCUser.logIn(t1, t2).subscribe(object : Observer<LCUser> {
                            override fun onSubscribe(d: Disposable) {

                            }

                            override fun onNext(t: LCUser) {
                                startA(LeanCLoudHome::class.java, "主页")
                                finish()
                            }

                            override fun onError(e: Throwable) {

                            }

                            override fun onComplete() {

                            }
                        })
                    }

                    //失败
                    override fun onError(e: Throwable) {
                        showToast("注册失败")
                    }

                    //完成
                    override fun onComplete() {

                    }
                })
            }
        }
    }
}