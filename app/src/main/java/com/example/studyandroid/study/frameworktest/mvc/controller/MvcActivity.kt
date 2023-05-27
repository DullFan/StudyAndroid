package com.example.studyandroid.study.frameworktest.mvc.controller

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.study.frameworktest.mvc.model.MvcBoard
import kotlinx.coroutines.Dispatchers

class MvcActivity : BaseActivity() {
    lateinit var model: MvcBoard
    lateinit var buttonGrid: ViewGroup
    lateinit var winnerPlayerViewGroup: View
    lateinit var winnerPlayerLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        winnerPlayerLabel = findViewById<TextView>(R.id.winnerPlayerLabel)
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup)
        buttonGrid = findViewById<ViewGroup>(R.id.buttonGrid)
        model = MvcBoard()
    }

    fun onCellClicked(view: View) {
        val button = view as Button
        val tag = button.tag.toString()
        val row = tag.substring(0, 1).toInt()
        val col = tag.substring(1, 2).toInt()
        val playerThatMoved = model.mark(row, col)
        if (playerThatMoved != null) {
            button.text = playerThatMoved.toString()
            if (model.winner != null) {
                winnerPlayerLabel.text = playerThatMoved.toString()
                winnerPlayerViewGroup.visibility = View.VISIBLE
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                model.restart()
                resetView()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_jingziqi, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun resetView() {
        winnerPlayerViewGroup.visibility = View.GONE
        winnerPlayerLabel.text = ""
        for (i in 0 until buttonGrid.childCount) {
            (buttonGrid.getChildAt(i) as Button).text = ""
        }
    }
}