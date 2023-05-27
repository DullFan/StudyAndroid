package com.example.studyandroid.study.smartrefreshlayout

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyandroid.R
import com.example.studyandroid.adapter.BaseRvAdapter
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivitySmartRefreshLayoutBinding
import com.example.studyandroid.databinding.ItemDataBindingAndRvBinding
import com.example.studyandroid.utils.showLog
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.header.MaterialHeader

class SmartRefreshLayoutActivity : BaseActivity() {
    val viewDataBinding by lazy {
        ActivitySmartRefreshLayoutBinding.inflate(layoutInflater)
    }


    var count = 20


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewDataBinding.root)

        val mutableListOf = mutableListOf<String>()
        repeat(count) {
            mutableListOf += "Item$it"
        }

        val rvAdapter =
            object : BaseRvAdapter<String>(R.layout.item_data_binding_and_rv, mutableListOf) {
                override fun onBind(rvDataBinding: ViewDataBinding, data: String, position: Int) {
                    rvDataBinding as ItemDataBindingAndRvBinding
                    rvDataBinding.data = data
                }
            }

        //设置刷新头
        viewDataBinding.smart.setRefreshHeader(BezierRadarHeader(this))
        //设置刷新尾巴
        viewDataBinding.smart.setRefreshFooter(BallPulseFooter(this))

        //上拉刷新加载
        viewDataBinding.smart.setOnRefreshListener {
            mutableListOf.clear()
            repeat(count) {
                mutableListOf += "Item$it"
            }
            rvAdapter.dataList = mutableListOf
            viewDataBinding.smart.finishRefresh()
        }

        //下拉加载监听
        viewDataBinding.smart.setOnLoadMoreListener {
            repeat(10) {
                mutableListOf += "Item"
            }
            rvAdapter.dataList = mutableListOf
            viewDataBinding.smart.finishLoadMore()
        }

        viewDataBinding.rv.layoutManager = LinearLayoutManager(this)
        viewDataBinding.rv.adapter = rvAdapter
    }
}