package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar
import com.draganstojanov.myworld_compose.viewModel.CountryDetailsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryDetailsScreen(
    navController: NavHostController,
    viewModel: CountryDetailsViewModel
) {
    val countryState = viewModel.countryState

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = countryState.value?.name?.common,
                navController = navController
            )
        }
    ) {

    }

}

