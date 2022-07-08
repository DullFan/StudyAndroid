package com.example.studyandroid.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue


val Float.px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.px: Float
    get() = this.toFloat().px