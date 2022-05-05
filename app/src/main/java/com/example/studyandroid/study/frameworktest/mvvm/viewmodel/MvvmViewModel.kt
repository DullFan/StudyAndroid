package com.example.studyandroid.study.frameworktest.mvvm.viewmodel

import android.util.ArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyandroid.study.frameworktest.mvvm.model.MvvmBoard

class MvvmViewModel : ViewModel() {
    var model: MvvmBoard = MvvmBoard()

    val cells = MutableLiveData<ArrayMap<String, String>>()
    val winner = MutableLiveData<String>()

    init {
        cells.postValue(ArrayMap<String, String>())
    }

    fun onResetSelected() {
        model.restart()
        winner.postValue(null)
        cells.value?.clear()
        cells.postValue(cells.value)
    }

    fun onClickedCellAt(row: Int, col: Int) {
        val mark = model.mark(row, col)
        if (mark != null) {
            cells.value?.put("${row}${col}", mark.toString())
            cells.postValue(cells.value)
            winner.postValue(if (model.winner == null) null else model.winner.toString())
        }
    }
}