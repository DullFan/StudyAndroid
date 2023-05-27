package com.example.studyandroid.study.xutils.annation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindView(val value: Int = 1)
