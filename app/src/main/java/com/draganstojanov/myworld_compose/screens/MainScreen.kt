package com.draganstojanov.myworld_compose.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.elements.ButtonStandard
import com.draganstojanov.myworld_compose.elements.ShowToast
import com.draganstojanov.myworld_compose.elements.SomethingWentWrongFullScreen
import com.draganstojanov.myworld_compose.ui.theme.colorElementBack
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.util.eventModel.FilterEvent
import com.draganstojanov.myworld_compose.util.eventModel.FilterEventType
import com.draganstojanov.myworld_compose.util.eventModel.FilterEventType.*
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
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

    ) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name), color = colorWhite) },
            backgroundColor = colorElementBack,
            actions = {
                IconButton(onClick = { ShowToast(context, message = "MY WORLD - COMPOSE") }) {
                    Icon(Icons.Filled.Info, "About", tint = colorWhite)
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.rounded_corner))
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            MainScreenCard(
                title = stringResource(id = R.string.continents),
                list = viewModel.continentsState.value.toList(),
                viewModel = viewModel,
                navController = navController,
                eventType = CONTINENT
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            MainScreenCard(
                title = stringResource(id = R.string.regions),
                list = viewModel.regionsState.value.toList(),
                viewModel = viewModel,
                navController = navController,
                eventType = REGION
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            MainScreenCard(
                title = stringResource(id = R.string.subregions),
                list = viewModel.subregionsState.value.toList(),
                viewModel = viewModel,
                navController = navController,
                eventType = SUBREGION
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            ButtonStandard(
                stringRes = R.string.all_countries,
                onCLick = {
                    viewModel.filterEvent(FilterEvent.All)
                    navController.navigate(NavScreens.CountryListScreen.name)
                })
        }
    }
}

@Composable
fun MainScreenCard(
    title: String,
    list: List<String>,
    viewModel: MainViewModel,
    navController: NavController,
    eventType: FilterEventType
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorWhite),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
        elevation = dimensionResource(id = R.dimen.elevation_value)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorWhite)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorElementBack)
                    .height(dimensionResource(id = R.dimen.element_height_standard)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = colorWhite
                )
            }

            LazyColumn {
                items(list) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.element_height_large)),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(onClick = {
                            when (eventType) {
                                CONTINENT -> viewModel.filterEvent(FilterEvent.Continent(item))
                                REGION -> viewModel.filterEvent(FilterEvent.Region(item))
                                SUBREGION -> viewModel.filterEvent(FilterEvent.Subregion(item))
                                else -> {}
                            }
                            navController.navigate(NavScreens.CountryListScreen.name)
                        }) {
                            Text(
                                fontWeight = FontWeight.ExtraBold,
                                text = item,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = colorElementBack
                            )
                        }
                    }
                }
            }
        }
    }
}








