package com.example.studyandroid.study.leancloud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.leancloud.LCObject
import com.example.studyandroid.R
import com.example.studyandroid.adapter.BaseRvAdapter
import com.example.studyandroid.databinding.ActivityLeanCloudHomeBinding
import com.example.studyandroid.databinding.LeanCloudHomeRvItemBinding

class LeanCLoudHome : AppCompatActivity() {
    lateinit var viewBind: ActivityLeanCloudHomeBinding
    lateinit var myViewModel: LeanCLoudViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityLeanCloudHomeBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        myViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                LeanCLoudViewModel::class.java
            )


        viewBind.homeAdd.setOnClickListener {
            myViewModel.addWord("添加的数据,随机数${(1..10000).shuffled().first()}")
        }
        viewBind.homeRv.layoutManager = LinearLayoutManager(this)
        viewBind.homeRv.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        myViewModel.dataListLive.observe(this){
            val rvAdapter = object : BaseRvAdapter<LCObject>(
                R.layout.lean_cloud_home_rv_item,
                it
            ) {
                override fun onBind(rvDataBinding: ViewDataBinding, data: LCObject, position: Int) {
                    rvDataBinding as LeanCloudHomeRvItemBinding
                    rvDataBinding.leanCloudHomeRvItemText.text = data.get("word").toString()
                }
            }
            viewBind.homeRv.adapter = rvAdapter
        }
    }
}