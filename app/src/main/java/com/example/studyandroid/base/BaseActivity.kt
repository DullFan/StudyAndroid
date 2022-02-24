package com.example.studyandroid.base

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.res.integerArrayResource

open class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        val stringExtra = intent.getStringExtra("title")
        if(stringExtra?.isNotEmpty() == true){
            supportActionBar?.title = stringExtra
        }
    }

    fun<T> startA(clazz:Class<T>,title:String){
        val intent = Intent(this, clazz)
        intent.putExtra("title",title)
        startActivity(intent)
    }

    fun <T> showToast(value:T){
        Toast.makeText(this,"$value", Toast.LENGTH_LONG).show()
    }


}