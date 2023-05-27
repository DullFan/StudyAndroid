package com.example.studyandroid.study.skinning

import android.Manifest
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.download.DownloadTaskListener
import com.arialyy.aria.core.task.DownloadTask
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivitySkinningBinding
import com.example.studyandroid.skin.SkinFlag
import com.example.studyandroid.skin.SkinManager
import com.example.studyandroid.utils.showLog
import java.lang.Exception
import java.util.Calendar

var dialogFlag = true

class Skinning : BaseActivity(), DownloadTaskListener {

    private val viewBinding by lazy { ActivitySkinningBinding.inflate(layoutInflater) }

    var permissions: Array<String> =
        arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES
        )

    private val launcherActivity =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[Manifest.permission.WRITE_EXTERNAL_STORAGE]!!) {

            } else {
                showToast("请开启权限")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        Aria.download(this).register()
        launcherActivity.launch(permissions)
        showLog("重新执行")
        viewBinding.skinningButton2.setOnClickListener {
            //获取缓存路径
            val skinName = "${baseContext.filesDir}/test.apk"
            SkinManager.getInstance().loadSkin(skinName)
        }

        viewBinding.skinningButton.setOnClickListener {
            SkinManager.getInstance().loadSkin(null)
        }

        viewBinding.dialogButton.setOnClickListener {
            SkinFlag.dialogFlag = false
            val c: Calendar = Calendar.getInstance()
            val year: Int = c.get(Calendar.YEAR)
            val month: Int = c.get(Calendar.MONTH)
            val day: Int = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                layoutInflater.context,
                null, year, month, day
            )
            dpd.show()

            dpd.setOnCancelListener {
                SkinFlag.dialogFlag = true
            }
        }

        viewBinding.skinningButton3.setOnClickListener {
            showToast("开始下载")

            Aria.download(this)
                .load("https://dullfan.oss-cn-beijing.aliyuncs.com/app-release.apk?versionId=CAEQHRiBgMC9_fnJgBgiIDIxY2FmNGIyZWRmNTQzMzg5NWRjODY3MjllNTNmMDQ3")
                .setFilePath("${baseContext.filesDir}/test.apk")
                //忽略文件占用，不管文件路径是否被其它任务占用，都执行上传\下载任务
                .ignoreFilePathOccupy()
                .create()
        }
    }

    //队列已经满了，继续创建任务，将会回调该方法
    override fun onWait(task: DownloadTask?) {

    }

    //预处理，有时有些地址链接比较慢，这时可以先在这个地方出来一些界面上的UI，如按钮的状态。
    //在这个回调中，任务是获取不到文件大小，下载速度等参数
    override fun onPre(task: DownloadTask?) {

    }

    //任务预加载完成
    override fun onTaskPre(task: DownloadTask?) {

    }

    //任务恢复下载
    override fun onTaskResume(task: DownloadTask?) {

    }

    //任务开始
    override fun onTaskStart(task: DownloadTask?) {

    }

    //任务停止
    override fun onTaskStop(task: DownloadTask?) {

    }

    //任务取消
    override fun onTaskCancel(task: DownloadTask?) {

    }

    //任务失败
    override fun onTaskFail(task: DownloadTask?, e: Exception?) {
        showToast("下载失败")
    }

    //任务完成
    override fun onTaskComplete(task: DownloadTask?) {
        showToast("下载完成")
    }

    //任务执行中
    override fun onTaskRunning(task: DownloadTask?) {
        Aria.get(this).downloadConfig.isConvertSpeed = true
//        Aria.get(this).downloadConfig.isConvertSpeed = false
        showLog("转换后文件大小：${task?.convertFileSize}")
        showLog("原文件大小：${task?.fileSize}")
        showLog("下载百分比：${task?.percent}")
        showLog("转换后下载速度：${task?.convertSpeed}")
        showLog("原下载速度：${task?.speed}")

        showLog("原下载剩余时间：${task?.timeLeft}")
        showLog("转换后下载剩余时间：${task?.convertTimeLeft}")
        showLog("转换后当前进度：${task?.convertCurrentProgress}")
        showLog("原当前进度：${task?.currentProgress}")
        showLog("------------------------------------------------------------")
    }

    override fun onNoSupportBreakPoint(task: DownloadTask?) {

    }
}