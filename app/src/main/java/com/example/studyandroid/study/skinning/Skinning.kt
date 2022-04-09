package com.example.studyandroid.study.skinning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skinlib.SkinManager
import com.example.studyandroid.databinding.ActivitySkinningBinding
import com.example.studyandroid.utils.showLog

class Skinning : AppCompatActivity() {

    private val viewBinding by lazy { ActivitySkinningBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.skinningButton2.setOnClickListener {
            //获取缓存路径
            val skinName = "${baseContext.filesDir}/app-release.skin"
            SkinManager.getInstance().loadSkin(skinName)
        }

        viewBinding.skinningButton.setOnClickListener {
            SkinManager.getInstance().loadSkin(null)
        }

        viewBinding.skinningButton3.setOnClickListener {
            val skinName = "${baseContext.filesDir}/app-release.skin"

        }

    }
}