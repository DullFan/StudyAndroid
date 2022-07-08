package com.example.studyandroid.study.databinding_and_rv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityDataBindingAndRvBinding

class DataBindingAndRvActivity : BaseActivity() {
    val viewDataBinding by lazy {
        ActivityDataBindingAndRvBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewDataBinding.root)

        val dataList = ArrayList<String>()
        repeat(100){
            dataList.add("Item $it")
        }
        with(viewDataBinding.databinginAndRvList){
            adapter = DataBindingAndRvAdapter(dataList)
        }
    }
}