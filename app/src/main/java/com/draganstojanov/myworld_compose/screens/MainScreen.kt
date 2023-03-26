package com.draganstojanov.myworld_compose.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.elements.ButtonStandard
import com.draganstojanov.myworld_compose.elements.SomethingWentWrongFullScreen
import com.draganstojanov.myworld_compose.util.eventModel.FilterEvent
import com.draganstojanov.myworld_compose.util.navigation.NavScreens
import com.draganstojanov.myworld_compose.viewModel.MainViewModel


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
        SomethingWentWrongFullScreen()
    }

}


@Composable
fun AllCountriesButton(
    viewModel: MainViewModel,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) },
            backgroundColor = MaterialTheme.colors.primary,
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Info, "About")
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_horizontal))
        ) {
            ButtonStandard(
                stringRes = R.string.all_countries,
                onCLick = {
                    viewModel.filterEvent(FilterEvent.All)
                    navController.navigate(NavScreens.CountryListScreen.name)
                })
        }

    }
}






