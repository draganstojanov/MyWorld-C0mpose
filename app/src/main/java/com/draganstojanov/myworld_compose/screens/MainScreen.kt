package com.draganstojanov.myworld_compose.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.util.eventModel.FilterEvent
import com.draganstojanov.myworld_compose.util.navigation.NavScreens
import com.draganstojanov.myworld_compose.viewModel.MainViewModel
import com.draganstojanov.myworld_compose.widgets.SomethingWentWrongFUllScreen

@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    val countries = viewModel.countriesState.value
    if (countries.isNotEmpty()) {
        AllCountriesButton(
            viewModel = viewModel,
            navController = navController
        )
    } else {
        SomethingWentWrongFUllScreen()
    }

}

@Composable
fun AllCountriesButton(
    viewModel: MainViewModel,
    navController: NavController
) {
    Button(onClick = {
        viewModel.filterEvent(FilterEvent.All)
        navController.navigate(NavScreens.CountryListScreen.name)
    }) {
        Text(stringResource(id = R.string.all_countries))
    }
}




