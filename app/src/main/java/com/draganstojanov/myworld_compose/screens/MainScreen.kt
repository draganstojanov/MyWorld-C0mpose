package com.draganstojanov.myworld_compose.screens

import android.util.Log
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
    Log.d("ADF-2", countries.toString())
    Text(
        modifier = Modifier.fillMaxWidth(),
       text = if(countries.isNotEmpty()) countries.size.toString() else "ASASASAS",
        fontSize = 24.sp,
        color = Color.Blue
    )

}


