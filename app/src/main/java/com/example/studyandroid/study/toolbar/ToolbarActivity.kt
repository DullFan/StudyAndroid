package com.example.studyandroid.study.toolbar

import android.os.Bundle
import com.example.studyandroid.base.BaseActivity
import android.view.Menu
import android.widget.SearchView

import com.example.studyandroid.utils.showLog
import androidx.core.view.MenuItemCompat

import androidx.appcompat.widget.ShareActionProvider
import com.example.studyandroid.R
import android.content.Intent





class ToolbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_toolbar_menu,menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "请搜索....."
        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.isIconified = true
                showLog(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return  false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


}