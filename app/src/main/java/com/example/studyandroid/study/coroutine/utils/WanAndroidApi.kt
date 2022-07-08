package com.example.studyandroid.study.coroutine.utils

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


val wanAndroidServiceApi: WanAndroidApi by lazy {
    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl("https://www.wanandroid.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    retrofit.create(WanAndroidApi::class.java)
}

interface WanAndroidApi {

    @GET("/article/list/0/json")
    suspend fun loadWanAndroidHomeListRequest(): HomeListBean
}
