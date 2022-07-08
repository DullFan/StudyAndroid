package com.example.studyandroid.utils

import android.app.Application
import cn.leancloud.LeanCloud
import com.example.studyandroid.skin.SkinManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //换肤
        SkinManager.init(this)
        // 提供 this、App ID、App Key、Server Host 作为参数
        // 注意这里千万不要调用 cn.leancloud.core.LeanCloud 的 initialize 方法，否则会出现 NetworkOnMainThread 等错误。
        LeanCloud.initialize(
            this,
            "EQfgRMF56zmjer78RV5KVViO-gzGzoHsz",
            "hjCGxYW1mLEPWvMjpy2cqHWe",
            "https://eqfgrmf5.lc-cn-n1-shared.com"
        );
    }
}