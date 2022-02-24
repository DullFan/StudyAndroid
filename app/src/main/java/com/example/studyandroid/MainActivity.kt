package com.example.studyandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyandroid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


    }
}