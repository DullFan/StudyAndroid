package com.example.studyandroid.study.coroutine

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.studyandroid.MainActivity
import com.example.studyandroid.R
import com.example.studyandroid.databinding.FragmentCoroutineHomeBinding
import com.example.studyandroid.study.coroutine.utils.LocalEventBus
import com.example.studyandroid.study.coroutine.utils.WanViewModel
import com.example.studyandroid.utils.showLog
import kotlinx.coroutines.flow.collect


class CoroutineHomeFragment : Fragment() {
    val viewDataBinding by lazy {
        FragmentCoroutineHomeBinding.inflate(layoutInflater)
    }

    val viewModel: WanViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(WanViewModel::class.java)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding.button01.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_coroutineHomeFragment_to_coroutineDownloadFragment)
        }

        viewDataBinding.button02.setOnClickListener {
            viewModel.getHomeListData()
            viewModel.wanHomeListData.observe(viewLifecycleOwner){
                showLog(it)
            }
        }

        viewDataBinding.apply {
            coroutineHomeAdd.setOnClickListener {
                viewModel.increment()
            }

            coroutineHomeReduce.setOnClickListener {
                viewModel.decrement()
            }
        }

        lifecycleScope.launchWhenCreated {
            //值如果发生了变化，这里会监听到
            viewModel.number.collect{value ->
                viewDataBinding.coroutineHomeNumber.text = "$value"
            }
        }


        viewDataBinding.apply {
            coroutineHomeStart.setOnClickListener {
                viewModel.startRefresh()
            }

            coroutineHomePause.setOnClickListener {
                viewModel.stopRefresh()
            }
        }

        lifecycleScope.launchWhenCreated {
            LocalEventBus.events.collect{
                viewDataBinding.coroutineHomeNumber01.text = it.timestamp.toString()
                viewDataBinding.coroutineHomeNumber02.text = it.timestamp.toString()
                viewDataBinding.coroutineHomeNumber03.text = it.timestamp.toString()
                viewDataBinding.coroutineHomeText.text = String.format(resources.getString(R.string.coroutine_string),it.timestamp)
            }
        }

        viewDataBinding.coroutineHomeText.text = String.format(resources.getString(R.string.coroutine_string),10)

        viewDataBinding.coroutineHomeTestButton.setOnClickListener {
            createNotificationForHigh()
        }

        return viewDataBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationForHigh() {
        val mManager = requireActivity().getSystemService(NOTIFICATION_SERVICE) as NotificationManager;
        val intent = Intent(requireActivity(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("11", "mHighChannelName", NotificationManager.IMPORTANCE_HIGH)
            channel.setShowBadge(true)
            mManager.createNotificationChannel(channel)
        }
        val mBuilder = NotificationCompat.Builder(requireContext(), "11")
            .setContentTitle("重要通知")
            .setContentText("重要通知内容")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setAutoCancel(true)
            .setNumber(999) // 自定义桌面通知数量
            .addAction(R.mipmap.ic_launcher, "去看看", pendingIntent)// 通知上的操作
            .setCategory(NotificationCompat.CATEGORY_MESSAGE) // 通知类别，"勿扰模式"时系统会决定要不要显示你的通知
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE) // 屏幕可见性，锁屏时，显示icon和标题，内容隐藏
        mManager.notify(11, mBuilder.build())
    }
}

fun View.findNavController(): NavController =
    Navigation.findNavController(this)