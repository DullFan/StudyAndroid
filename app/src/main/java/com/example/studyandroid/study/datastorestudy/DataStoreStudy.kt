package com.example.studyandroid.study.datastorestudy

import android.content.Context
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.databinding.ActivityDataStoreStudyBinding
import java.util.prefs.Preferences



class DataStoreStudy : BaseActivity() {

    val viewBinding by lazy {
        ActivityDataStoreStudyBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)





    }
}


class DataStoreManager(context: Context){

}