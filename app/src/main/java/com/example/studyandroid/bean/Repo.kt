package com.example.studyandroid.bean

data class Repo(
    val code: Int,
    val msg: String,
    val newslist: List<Newslist>
)

data class Newslist(
    val area: String,
    val date: String,
    val highest: String,
    val humidity: String,
    val lowest: String,
    val moondown: String,
    val moonrise: String,
    val pcpn: String,
    val pop: String,
    val real: String,
    val sunrise: String,
    val sunset: String,
    val tips: String,
    val uv_index: String,
    val vis: String,
    val weather: String,
    val weatherimg: String,
    val week: String,
    val wind: String,
    val winddeg: String,
    val windsc: String,
    val windspeed: String
)