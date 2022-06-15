package com.example.studyandroid.api

import com.example.studyandroid.bean.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.Serializable

interface GitHubService :Serializable{
    @GET("tianqi/index")
    fun listRepos(@Query("city") city: String, @Query("key") key: String): Call<Repo>
}