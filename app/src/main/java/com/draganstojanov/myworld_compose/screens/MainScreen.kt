package com.draganstojanov.myworld_compose.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.draganstojanov.myworld_compose.viewModel.MainViewModel


@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val countries = viewModel.countriesState.value
    if (countries.isNotEmpty()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = countries[0].flag.toString(),//todo
            fontSize = 24.sp,
            color = Color.Blue
        )
    }

}




