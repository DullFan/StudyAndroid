package com.example.studyandroid.utils

import android.util.Log

const val TAG = "showLog"

fun <T> showLog(value:T){
    Log.i("showLog","$value")
}