package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.model.Native
import com.draganstojanov.myworld_compose.ui.theme.colorGreyPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.viewModel.CountryDetailsViewModel

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
        }
    ) {
        CountryDetails(countryState, nativeNamesState)
    }

}

@Composable
fun CountryDetails(countryState: MutableState<Country?>, nativeNamesState: MutableState<List<Native>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.padding_horizontal))
    ) {
        NameBLock(countryState, nativeNamesState)
        FlagAndCoatOfArms(countryState)
    }

}

@Composable
fun FlagAndCoatOfArms(countryState: MutableState<Country?>) {
    Row(
        modifier = Modifier
            // .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Box(modifier = Modifier.weight(1F, true)) {
            AsyncImage(
                model = countryState.value?.flags?.png,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Flag",
                contentScale = ContentScale.FillBounds

            )
        }
        Box(modifier = Modifier.weight(2F, true)) {
            SubcomposeAsyncImage(
                model = countryState.value?.coatOfArms?.png,
                contentDescription = null,
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun NameBLock(countryState: MutableState<Country?>, nativeNamesState: MutableState<List<Native>>) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .fillMaxWidth()
            .background(colorSecondary)

    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            text = countryState.value?.name?.common.toString(),
            style = MaterialTheme.typography.h5,
            color = colorWhite,
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = countryState.value?.name?.official.toString(),
            style = MaterialTheme.typography.body1,
            color = colorWhite,
        )
        Divider(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = colorGreyPrimary,
        )
        repeat(nativeNamesState.value.size) {
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp),
                text = nativeNamesState.value[it].common.toString(),
                style = MaterialTheme.typography.h6,
                color = colorWhite,
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = nativeNamesState.value[it].official.toString(),
                style = MaterialTheme.typography.body2,
                color = colorWhite,
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}


