package com.draganstojanov.myworld_compose.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.viewModel.MainViewModel
import com.draganstojanov.myworld_compose.widgets.SomethingWentWrongFUllScreen

@Composable
fun CountryListScreen(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    val countryList = viewModel.filteredCountryList

    SomethingWentWrongFUllScreen()//TODO test

}