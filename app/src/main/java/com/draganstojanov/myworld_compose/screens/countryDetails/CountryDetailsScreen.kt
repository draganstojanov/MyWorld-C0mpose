package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.composables.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.model.main.Native
import com.draganstojanov.myworld_compose.viewModel.CountryDetailsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CountryDetailsScreen(
    onOpenMap: (String, String) -> Unit,
    onBackPressed: () -> Boolean,
    viewModel: CountryDetailsViewModel
) {
    val countryState = viewModel.countryState
    val nativeNamesState = viewModel.nativeNamesList

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = countryState.value?.name?.common,
                onBackPressed = { onBackPressed() }
            )
        }
    ) {
        CountryDetails(
            it,
            onOpenMap,
            countryState,
            nativeNamesState,
            viewModel
        )
    }
}

@Composable
fun CountryDetails(
    contentPadding: PaddingValues,
    onOpenMap: (String, String) -> Unit,
    countryState: MutableState<Country?>,
    nativeNamesState: MutableState<List<Native>>,
    viewModel: CountryDetailsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_horizontal),
                end = dimensionResource(id = R.dimen.padding_horizontal),
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding()
            )
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.size(16.dp))
        NameBlock(countryState, nativeNamesState)
        FlagAndCoatOfArms(countryState)

        val title = countryState.value?.name?.common.toString()
        val googleMaps = URLEncoder.encode(countryState.value?.maps?.googleMaps.toString(), StandardCharsets.UTF_8.toString())
        val openStreetMaps = URLEncoder.encode(countryState.value?.maps?.openStreetMaps.toString(), StandardCharsets.UTF_8.toString())

        MapLinks(
            { onOpenMap(title, googleMaps) },
            { onOpenMap(title, openStreetMaps) })
        GeoData(countryState, viewModel)
        InfoData(countryState, viewModel)
    }
}


