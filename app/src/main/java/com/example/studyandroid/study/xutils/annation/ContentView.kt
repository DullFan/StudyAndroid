package com.example.studyandroid.study.xutils.annation

//注解作用域到类上
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
//注解保留到运行时期
@Retention(AnnotationRetention.RUNTIME)
// 你的布局 就是 int id值
annotation class ContentView(val value: Int = -1){



}