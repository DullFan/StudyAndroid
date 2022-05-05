package com.example.studyandroid.study.compose_study.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun titleComposable(titleComposableModel: TitleComposableModel) {
    Text(
        text = titleComposableModel.title,
        color = Color.Black,
        fontSize = 25.sp,
        modifier = Modifier.padding(15.dp)
    )

    val pxValue = with(LocalDensity.current) { 1.dp.toPx() }

    Spacer(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    )

}


@Composable
fun titlePictureComposable(titleComposableModel: TitleComposableModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(data = titleComposableModel.pictureUrl),
            contentDescription = "",
            modifier = Modifier
                .requiredHeight(220.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = titleComposableModel.title,
            color = Color.Black,
            fontSize = 25.sp,
            modifier = Modifier.padding(15.dp)
        )
    }
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    )
}