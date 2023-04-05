package com.draganstojanov.myworld_compose.screens.countryDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.model.main.Native
import com.draganstojanov.myworld_compose.util.navigation.NavScreens
import com.draganstojanov.myworld_compose.viewModel.CountryDetailsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryDetailsScreen(
    navController: NavHostController,
    viewModel: CountryDetailsViewModel
) {
    val countryState = viewModel.countryState
    val nativeNamesState = viewModel.nativeNamesList

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = countryState.value?.name?.common,
                navController = navController
            )
        }, content = {
            CountryDetails(
                navController,
                countryState,
                nativeNamesState,
                viewModel
            )
        }
    )
}

@Composable
fun CountryDetails(
    navController: NavHostController,
    countryState: MutableState<Country?>,
    nativeNamesState: MutableState<List<Native>>,
    viewModel: CountryDetailsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.padding_horizontal))
            .verticalScroll(rememberScrollState())
    ) {
        NameBlock(countryState, nativeNamesState)
        FlagAndCoatOfArms(countryState)

        val title = countryState.value?.name?.common.toString()
        val googleMaps = URLEncoder.encode(countryState.value?.maps?.googleMaps.toString(), StandardCharsets.UTF_8.toString())
        val openStreetMaps = URLEncoder.encode(countryState.value?.maps?.openStreetMaps.toString(), StandardCharsets.UTF_8.toString())

        MapLinks(
            { navController.navigate("${NavScreens.MapScreen.name}/${title}/${googleMaps}") },
            { navController.navigate("${NavScreens.MapScreen.name}/${title}/${openStreetMaps}") })
        GeoData(countryState, viewModel)
    }
}


