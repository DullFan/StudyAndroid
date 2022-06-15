package com.example.studyandroid.study.datastorestudy

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    //SharedPreferences文件名
    const val MY_SP = "mySP"

    //DataStore<>文件名
    const val MY_PREFERENCES = "myPreferences"

    //SharedPreferences 迁移到 DataStore<Preferences> 后的文件名
    const val SP_2_PREFERENCES = "sp2Preferences"

    //SharedPreferences中的key
    const val KEY_NAME_SP = "name"

    //SharedPreferences 迁移到 DataStore<Preferences> 后的key
    val KEY_NAME = stringPreferencesKey(KEY_NAME_SP)

    //DataStore<Preferences> 中的key
    val KEY_USER_NAME = stringPreferencesKey("userName")
    val KEY_USER_AGE = intPreferencesKey("userAge")
}
