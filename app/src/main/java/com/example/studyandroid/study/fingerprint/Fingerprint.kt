package com.example.studyandroid.study.fingerprint

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.studyandroid.base.BaseActivity
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.core.os.CancellationSignal
import com.example.studyandroid.databinding.ActivityFingerprintBinding
import com.example.studyandroid.utils.showLog


class Fingerprint : BaseActivity() {

    lateinit var viewBinding: ActivityFingerprintBinding

    lateinit var cancellationSignal: CancellationSignal

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFingerprintBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.pictureButton.setOnClickListener {
            check(viewBinding.pictureButton)
        }
    }

    /**
     * 第一个参数是用于通过指纹验证取出AndroidKeyStore中的key的对象。crypto这是一个加密类的对象，指纹扫描器会使用这个对象来判断认证结果的合法性。
     * 这个对象可以是null，但是这样的话，就意味这app无条件信任认证的结果，虽然从理论上这个过程可能被攻击，数据可以被篡改，这是app在这种情况下必须承担的风险。
     * 因此，建议这个参数不要置为null。这个类的实例化有点麻烦，主要使用javax的security接口实现，
     * 通常来讲，用来加密指纹扫描结果的机制就是一个Javax.Crypto.Cipher对象。Cipher对象本身会使用由应用调用Android keystore的API产生一个key来实现上面说道的保护功能
     *
     * 第二个参数可以用来取消指纹验证，如果想手动关闭验证，可以调用该参数的cancel方法。
     *
     * 第三个参数没什么意义，就是传0就好了。
     *
     * 第四个参数最重要，由于指纹信息是存在系统硬件中的，app是不可以访问指纹信息的，所以每次验证的时候，系统会通过这个callback告诉你是否验证通过、验证失败等。
     * 第四个参数是handler，fingerprint中的消息都通过这个handler来传递消息，如果你传空，则默认创建一个在主线程上的handler来传递消息，没什么用，0传null好了。
     */
    fun check(view: View?) {
        val fingerprintManager = FingerprintManagerCompat.from(this)
        cancellationSignal = CancellationSignal()

        //判断指纹功能是否可用,需要添加权限USE_FINGERPRINT
        if (fingerprintManager.isHardwareDetected) {
            //手机中是否有已经登记的指纹，即是否录入过指纹，如果没有需要先输入密码在录入指纹
            if (fingerprintManager.hasEnrolledFingerprints()) {
                //启动指纹识别
                fingerprintManager.authenticate(
                    null,
                    0,
                    cancellationSignal,
                    object : FingerprintManagerCompat.AuthenticationCallback() {
                        override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
                            super.onAuthenticationError(errMsgId, errString)
                            showToast("身份验证失败")
                        }

                        override fun onAuthenticationHelp(
                            helpMsgId: Int,
                            helpString: CharSequence
                        ) {
                            super.onAuthenticationHelp(helpMsgId, helpString)
                            showToast("$helpString")
                        }

                        override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            showToast("验证成功")
                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            showToast("验证成功")
                        }
                    },
                    null
                )
            } else {
                showToast("你还没设置指纹呢！")
            }
        } else {
            showToast("该手机无法使用指纹识别功能")
        }
    }

    //取消指纹验证
    fun cancel(view: View?) {
        cancellationSignal.cancel()
    }

}