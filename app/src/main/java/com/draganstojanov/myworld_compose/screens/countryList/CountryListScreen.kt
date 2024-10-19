package com.draganstojanov.myworld_compose.screens.countryList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.composables.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.viewModel.CountryListViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountryListScreen(
    title: String?,
    viewModel: CountryListViewModel,
    onCountryDetails: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val countryList = viewModel.searchFilteredList

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                onBackPressed = { onBackPressed() }
            )
        }
    ) {
        CountriesListLayout(
            paddingValues = it,
            viewModel = viewModel,
            countryList = countryList
        ) {
            onCountryDetails(it)
        }
    }
}

@Composable
fun CountriesListLayout(
    paddingValues: PaddingValues,
    viewModel: CountryListViewModel,
    countryList: MutableState<List<Country>>,
    onCountrySelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_horizontal),
                end = dimensionResource(id = R.dimen.padding_horizontal),
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        SearchTextField(viewModel = viewModel)
        ListOfCountries(viewModel, countryList, onCountrySelected)
    }
}



