package com.example.studyandroid.study.frameworktest.mvp.view

interface MvpView {
    fun showWinner(winningPlayerDisplayLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(row:Int,col:Int,string:String,)
}