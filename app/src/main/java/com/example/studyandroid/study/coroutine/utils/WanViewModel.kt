package com.example.studyandroid.study.coroutine.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WanViewModel : ViewModel() {
    var wanHomeListData: MutableLiveData<HomeListBean> = MutableLiveData()

    val number = MutableStateFlow(0)

    fun increment(){
        number.value++
    }

    fun decrement(){
        number.value--
    }

    private lateinit var job: Job

    fun startRefresh(){
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true){
                LocalEventBus.postEvent(Event(System.currentTimeMillis()))
            }
        }
    }

    fun stopRefresh(){
        job.cancel()
    }


    fun getHomeListData() {
        viewModelScope.launch {
            flow {
                emit(wanAndroidServiceApi.loadWanAndroidHomeListRequest())
            }.flowOn(Dispatchers.IO).catch { e ->
                e.printStackTrace()
            }.collect {
                wanHomeListData.value = it
            }
        }
    }

}