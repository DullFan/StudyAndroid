package com.example.studyandroid.study.loadsir.callback

import com.example.studyandroid.R
import com.kingja.loadsir.callback.Callback

class ErrorCallback :Callback(){
    override fun onCreateView(): Int {
        return R.layout.call_back_error
    }
}