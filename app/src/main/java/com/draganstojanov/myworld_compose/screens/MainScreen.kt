package com.draganstojanov.myworld_compose.screens


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
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
        AllCountries(
            viewModel = viewModel,
            navController = navController
        )
    } else {
        SomethingWentWrongFullScreen()
    }

}


@Composable
fun AllCountries(
    viewModel: MainViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name), color = colorWhite) },
                backgroundColor = colorPrimary,
                actions = {
                    IconButton(onClick = { ShowToast(context, message = "MY WORLD - COMPOSE") }) {
                        Icon(Icons.Filled.Info, "About", tint = colorWhite)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_horizontal),
                    end = dimensionResource(id = R.dimen.padding_horizontal),
                    top = 16.dp
                )
                .verticalScroll(rememberScrollState())

        ) {

            MainScreenCard(
                title = stringResource(id = R.string.continents),
                list = viewModel.continentsState.value.toList(),
                viewModel = viewModel,
                navController = navController,
                eventType = CONTINENT
            )

            MainScreenCard(
                title = stringResource(id = R.string.regions),
                list = viewModel.regionsState.value.toList(),
                viewModel = viewModel,
                navController = navController,
                eventType = REGION
            )

            MainScreenCard(
                title = stringResource(id = R.string.subregions),
                list = viewModel.subregionsState.value.toList(),
                viewModel = viewModel,
                navController = navController,
                eventType = SUBREGION
            )

            ButtonStandard(
                modifier = Modifier.padding(bottom = 32.dp, top = 16.dp),
                stringRes = R.string.all_countries,
                onCLick = {
                    viewModel.filterEvent(FilterEvent.All)
                    navController.navigate(NavScreens.CountryListScreen.name)
                })

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
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
            .background(color = colorWhite)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
        elevation = dimensionResource(id = R.dimen.elevation_value)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorWhite)
                .border(
                    width = 4.dp,
                    color = colorPrimary,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorPrimary)
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

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                for (item in list) {
                    Card(
                        backgroundColor = colorSecondary,
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                viewModel.filterEvent(getFilterEvent(item, eventType))
                                navController.navigate(NavScreens.CountryListScreen.name)
                            },
                        elevation = dimensionResource(id = R.dimen.elevation_value),

                        ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = colorWhite,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun getFilterEvent(item: String, eventType: FilterEventType): FilterEvent {
    return when (eventType) {
        CONTINENT -> FilterEvent.Continent(item)
        REGION -> FilterEvent.Region(item)
        SUBREGION -> FilterEvent.Subregion(item)
        else -> FilterEvent.All
    }
}








