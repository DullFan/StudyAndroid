package com.example.studyandroid.study.frameworktest.mvc.model

import com.example.studyandroid.utils.showLog

class MvcBoard {

    private val mvcCell: Array<Array<MvcCell?>> = Array<Array<MvcCell?>>(3) {
        arrayOfNulls<MvcCell>(
            3
        )
    }

    var winner: MvcPlayer? = null

    var currentTurn: MvcPlayer? = null

    var state: MvcGameState? = null

    init {
        restart()
    }

    /**
     * 开始新游戏，清除计分和状态
     */
    fun restart() {
        for (i in 0..2) {
            for (j in 0..2) {
                mvcCell[i][j] = MvcCell()
            }
        }
        winner = null
        currentTurn = MvcPlayer.X
        state = MvcGameState.IN_PROGRESS
    }

    fun mark(row: Int, col: Int): MvcPlayer? {
        var playerThatMoved: MvcPlayer? = null
        if (isValid(row, col)) {
            mvcCell[row][col]?.mvcPlayer = currentTurn
            playerThatMoved = currentTurn
            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                state = MvcGameState.FINISHED
                winner = currentTurn
            } else {
                showLog("进入错误")
                flipCurrentTurn()
            }
        }
        return playerThatMoved
    }

    private fun flipCurrentTurn() {
        currentTurn = if (currentTurn == MvcPlayer.X) {
            MvcPlayer.O
        } else {
            MvcPlayer.X
        }
    }

    private fun isWinningMoveByPlayer(
        currentTurn: MvcPlayer?,
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
        if (state == MvcGameState.FINISHED) {
            return false
        } else return !isCellValueAlreadySet(row, col)
    }

    private fun isCellValueAlreadySet(row: Int, col: Int): Boolean {
        return mvcCell[row][col]?.mvcPlayer != null
    }
}