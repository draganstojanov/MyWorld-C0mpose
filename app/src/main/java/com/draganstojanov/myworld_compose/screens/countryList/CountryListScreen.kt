package com.draganstojanov.myworld_compose.screens.countryList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.util.navigation.NavScreens
import com.draganstojanov.myworld_compose.viewModel.CountryListViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryListScreen(
    navController: NavHostController,
    title: String?,
    viewModel: CountryListViewModel
) {
    val countryList = viewModel.searchFilteredList

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                navController = navController
            )
        }, content = {
            CountriesListLayout(
                viewModel = viewModel,
                countryList = countryList
            ) {
                navController.navigate("${NavScreens.CountryDetailsScreen.name}/${it}")
            }
        }
    )
}

@Composable
fun CountriesListLayout(
    viewModel: CountryListViewModel,
    countryList: MutableState<List<Country>>,
    onCountrySelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal))
    ) {
        SearchTextField(viewModel = viewModel)
        ListOfCountries(viewModel, countryList, onCountrySelected)
    }
}



