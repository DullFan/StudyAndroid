package com.example.studyandroid.study.download

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.ui.res.integerArrayResource
import androidx.core.content.FileProvider
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.download.DownloadTaskListener
import com.arialyy.aria.core.task.DownloadTask
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityDownloadBinding
import com.example.studyandroid.utils.showLog
import java.io.File
import java.lang.Exception

const val DOWNLOAD_URL =
    "https://down.qq.com/qqweb/QQlite/Android_apk/qqlite_4.0.1.1060_537064364.apk"

class DownloadActivity : BaseActivity(), DownloadTaskListener {
    private val viewBind by lazy { ActivityDownloadBinding.inflate(layoutInflater) }
    var taskId: Long = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBind.root)
        Aria.download(this).register();

        viewBind.downloadButton1.setOnClickListener {
            taskId = Aria.download(this).load(DOWNLOAD_URL)
                .ignoreFilePathOccupy()
                .setFilePath("${baseContext.cacheDir}/download.apk").create()
        }

        viewBind.downloadButton2.setOnClickListener {
            //停止任务
            Aria.download(this).load(taskId).stop()
        }

        viewBind.downloadButton3.setOnClickListener {
            //恢复任务
            Aria.download(this).load(taskId).resume()
        }

        viewBind.downloadButton4.setOnClickListener {
            //删除单个任务
            Aria.download(this).load(taskId).cancel(true);
            viewBind.downloadText.text = "0mb/0mb"
            viewBind.downloadText2.text = "删除成功"
            viewBind.linearProgress.setProgress(0)
        }

        viewBind.downloadButton5.setOnClickListener {
            //apk的位置
            val file = File("${baseContext.cacheDir}/download.apk")
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            //8.0需要额外管理允许本应用安装应用程序 在上面down的时候检测安装权限 没权限可能不会跳转到安装页
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //在AndroidManifest中的android:authorities值
                val url = FileProvider.getUriForFile(this, "${packageName}.fileprovider", file)
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.setDataAndType(url, "application/vnd.android.package-archive")
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            }
            startActivity(intent)
        }
    }

    override fun onWait(task: DownloadTask?) {

    }

    override fun onPre(task: DownloadTask?) {

    }

    override fun onTaskPre(task: DownloadTask?) {

    }

    override fun onTaskResume(task: DownloadTask?) {

    }

    override fun onTaskStart(task: DownloadTask?) {

    }

    override fun onTaskStop(task: DownloadTask?) {

    }

    override fun onTaskCancel(task: DownloadTask?) {

    }

    override fun onTaskFail(task: DownloadTask?, e: Exception?) {

    }

    override fun onTaskComplete(task: DownloadTask?) {
        showToast("下载完成")
        if (task != null) {
            viewBind.downloadText.text = "${task.convertFileSize}/${task.convertFileSize}"
            viewBind.downloadText2.text = "下载速度:${task.convertSpeed} ----- 剩余时间:00:00"
            viewBind.linearProgress.setProgress(100)
        }
    }

    override fun onTaskRunning(task: DownloadTask?) {
        if (task != null) {
            viewBind.downloadText.text = "${task.convertCurrentProgress}/${task.convertFileSize}"
            viewBind.downloadText2.text =
                "下载速度:${task.convertSpeed} ----- 剩余时间:${task.convertTimeLeft}"
            viewBind.linearProgress.setProgress(task.percent)
        }
    }

    override fun onNoSupportBreakPoint(task: DownloadTask?) {

    }
}