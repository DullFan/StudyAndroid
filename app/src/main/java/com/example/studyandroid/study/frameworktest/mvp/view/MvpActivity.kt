package com.example.studyandroid.study.frameworktest.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.study.frameworktest.mvp.presenter.MvpPresenter

class MvpActivity : BaseActivity(), MvpView {
    lateinit var buttonGrid: ViewGroup
    lateinit var winnerPlayerViewGroup: View
    lateinit var winnerPlayerLabel: TextView

    var presenter: MvpPresenter = MvpPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel)
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup)
        buttonGrid = findViewById<ViewGroup>(R.id.buttonGrid)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                presenter.onResetSelected()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_jingziqi, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onCellClicked(view: View) {
        val button: Button = view as Button
        val tag = button.tag.toString()
        val row = tag.substring(0, 1).toInt()
        val col = tag.substring(1, 2).toInt()
        presenter.onButtonSelected(row, col)
    }

    override fun showWinner(winningPlayerDisplayLabel: String) {
        winnerPlayerLabel.text = winningPlayerDisplayLabel
        winnerPlayerViewGroup.visibility = View.VISIBLE
    }

    override fun clearWinnerDisplay() {
        winnerPlayerViewGroup.visibility = View.GONE
        winnerPlayerLabel.text = ""
    }

    override fun clearButtons() {
        for (i in 0 until buttonGrid.childCount) {
            ((buttonGrid.getChildAt(i)) as Button).text = ""
        }
    }

    override fun setButtonText(row: Int, col: Int, text: String) {
        val btn = buttonGrid.findViewWithTag<Button>("${row}${col}")
        if (btn != null) {
            btn.text = text
        }
    }
}