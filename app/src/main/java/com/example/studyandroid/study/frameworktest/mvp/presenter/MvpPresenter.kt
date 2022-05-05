package com.example.studyandroid.study.frameworktest.mvp.presenter

import com.example.studyandroid.study.frameworktest.mvp.model.MvpBoard
import com.example.studyandroid.study.frameworktest.mvp.view.MvpView

class MvpPresenter(val view: MvpView) {
    private val model: MvpBoard = MvpBoard()

    fun onButtonSelected(row: Int, col: Int) {
        val playerThatMoved = model.mark(row, col)
        if (playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString())
            if (model.winner != null) {
                view.showWinner(playerThatMoved.toString())
            }
        }
    }

    fun onResetSelected() {
        model.restart()
        view.clearWinnerDisplay()
        view.clearButtons()
    }
}