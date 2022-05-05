package com.example.studyandroid.study.frameworktest.mvvm.model

class MvvmCell() {
    var mvcPlayer: MvvmPlayer? = null
        get() {
            return field
        }
        set(value) {
            field = value
        }
}