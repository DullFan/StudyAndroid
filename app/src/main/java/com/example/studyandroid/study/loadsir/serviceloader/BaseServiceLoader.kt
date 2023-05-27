package com.example.studyandroid.study.loadsir.serviceloader

import java.util.*

object BaseServiceLoader {

    fun <S> load(service: Class<S>): S? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception) {
            null
        }
    }

}