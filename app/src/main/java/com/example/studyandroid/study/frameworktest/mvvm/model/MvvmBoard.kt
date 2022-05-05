package com.example.studyandroid.study.frameworktest.mvvm.model

import com.example.studyandroid.utils.showLog

class MvvmBoard {

    private val mvcCell: Array<Array<MvvmCell?>> = Array<Array<MvvmCell?>>(3) {
        arrayOfNulls<MvvmCell>(
            3
        )
    }

    var winner: MvvmPlayer? = null

    var currentTurn: MvvmPlayer? = null

    var state: MvvmGameState? = null

    init {
        restart()
    }

    /**
     * 开始新游戏，清除计分和状态
     */
    fun restart() {
        for (i in 0..2) {
            for (j in 0..2) {
                mvcCell[i][j] = MvvmCell()
            }
        }
        winner = null
        currentTurn = MvvmPlayer.X
        state = MvvmGameState.IN_PROGRESS
    }

    fun mark(row: Int, col: Int): MvvmPlayer? {
        var playerThatMoved: MvvmPlayer? = null
        if (isValid(row, col)) {
            mvcCell[row][col]?.mvcPlayer = currentTurn
            playerThatMoved = currentTurn
            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                state = MvvmGameState.FINISHED
                winner = currentTurn
            } else {
                showLog("进入错误")
                flipCurrentTurn()
            }
        }
        return playerThatMoved
    }

    private fun flipCurrentTurn() {
        currentTurn = if (currentTurn == MvvmPlayer.X) {
            MvvmPlayer.O
        } else {
            MvvmPlayer.X
        }
    }

    private fun isWinningMoveByPlayer(
        currentTurn: MvvmPlayer?,
        currentRow: Int,
        currentCol: Int
    ): Boolean {
        return (mvcCell.get(currentRow).get(0)?.mvcPlayer === currentTurn
                && mvcCell.get(currentRow).get(1)?.mvcPlayer === currentTurn && mvcCell.get(
            currentRow
        )
            .get(2)?.mvcPlayer === currentTurn
                ) || (mvcCell.get(0).get(currentCol)?.mvcPlayer === currentTurn
                && mvcCell.get(1).get(currentCol)?.mvcPlayer === currentTurn && mvcCell.get(2)
            .get(currentCol)?.mvcPlayer === currentTurn
                ) || (currentRow == currentCol
                && mvcCell.get(0).get(0)?.mvcPlayer === currentTurn && mvcCell.get(1).get(1)
            ?.mvcPlayer === currentTurn && mvcCell.get(2).get(2)?.mvcPlayer === currentTurn
                ) || (currentRow + currentCol == 2
                && mvcCell.get(0).get(2)?.mvcPlayer === currentTurn && mvcCell.get(1).get(1)
            ?.mvcPlayer === currentTurn && mvcCell.get(2).get(0)?.mvcPlayer === currentTurn)

    }

    private fun isValid(row: Int, col: Int): Boolean {
        if (state == MvvmGameState.FINISHED) {
            return false
        } else return !isCellValueAlreadySet(row, col)
    }

    private fun isCellValueAlreadySet(row: Int, col: Int): Boolean {
        return mvcCell[row][col]?.mvcPlayer != null
    }
}