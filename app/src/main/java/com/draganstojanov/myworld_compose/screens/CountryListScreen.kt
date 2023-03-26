package com.draganstojanov.myworld_compose.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.viewModel.MainViewModel
import com.draganstojanov.myworld_compose.elements.SomethingWentWrongFullScreen

@Composable
fun CountryListScreen(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    val countryList = viewModel.filteredCountryList

    SomethingWentWrongFullScreen()//TODO test

}