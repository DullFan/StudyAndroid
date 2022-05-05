package com.example.studyandroid.study.compose_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateListOf
import com.example.studyandroid.R
import com.example.studyandroid.base.BaseActivity
import com.example.studyandroid.study.compose_study.composables.TitleComposableModel
import com.example.studyandroid.study.compose_study.composables.titleComposable
import com.example.studyandroid.study.compose_study.composables.titlePictureComposable

class ComposeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = mutableStateListOf<TitleComposableModel>()
        list.add(TitleComposableModel("标题1","http://42.193.118.40:8080/image/ff782f9d-5964-4b52-b19f-d119153b1561.jpg"))
        list.add(TitleComposableModel("标题2",""))
        list.add(TitleComposableModel("标题3",""))
        list.add(TitleComposableModel("标题4","http://42.193.118.40:8080/image/ff782f9d-5964-4b52-b19f-d119153b1561.jpg"))
        list.add(TitleComposableModel("标题5",""))
        list.add(TitleComposableModel("标题6",""))
        list.add(TitleComposableModel("标题7","http://42.193.118.40:8080/image/ff782f9d-5964-4b52-b19f-d119153b1561.jpg"))
        list.add(TitleComposableModel("标题8",""))
        setContent {
            val listState = rememberLazyListState()
            LazyColumn(state = listState){
                items(list.size){
                    if(list[it].pictureUrl.isBlank()){
                        titleComposable(list[it])
                    }else{
                        titlePictureComposable(list[it])
                    }
                }
            }

            Button(onClick = {
                list.add(TitleComposableModel("标题7","http://42.193.118.40:8080/image/ff782f9d-5964-4b52-b19f-d119153b1561.jpg"))
            }) {
                Text(text = "添加数据")
            }
        }
    }
}