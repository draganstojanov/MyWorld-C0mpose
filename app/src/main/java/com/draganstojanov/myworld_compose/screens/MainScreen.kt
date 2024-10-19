package com.draganstojanov.myworld_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.composables.ButtonStandard
import com.draganstojanov.myworld_compose.composables.CustomTopAppBar
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.util.constants.FilterEventType
import com.draganstojanov.myworld_compose.util.constants.FilterEventType.ALL
import com.draganstojanov.myworld_compose.util.constants.FilterEventType.CONTINENT
import com.draganstojanov.myworld_compose.util.constants.FilterEventType.REGION
import com.draganstojanov.myworld_compose.util.constants.FilterEventType.SUBREGION
import com.draganstojanov.myworld_compose.viewModel.MainViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MainScreen(
    onBackPressed: () -> Unit,
    onNavigateToCountryListScreen: (String?, String?) -> Unit,
    viewModel: MainViewModel,

    ) {
    val countries = viewModel.countriesState.value
    if (countries.isNotEmpty()) {
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = stringResource(id = R.string.app_name),
                    onBackPressed = onBackPressed,
                    hasBackButton = false
                )
            }
        ) { paddingValues ->
            AllCountries(
                contentPadding = paddingValues,
                viewModel = viewModel
            ) {
                var cList: String = Json.encodeToString(viewModel.filteredCountryList.value)
                cList = cList.replace("/", "*#=@*")
                onNavigateToCountryListScreen(cList, it)
            }
        }
    }
}


@Composable
fun AllCountries(
    contentPadding: PaddingValues,
    viewModel: MainViewModel,
    onSelectItem: (String?) -> Unit
) {
    val allCountries = stringResource(id = R.string.all_countries)
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
        Spacer(modifier = Modifier.size(16.dp))
        MainScreenCard(
            title = stringResource(id = R.string.continents),
            list = viewModel.continentsState.value.toList(),
            viewModel = viewModel,
            eventType = CONTINENT,
            onSelectItem = onSelectItem
        )

        MainScreenCard(
            title = stringResource(id = R.string.regions),
            list = viewModel.regionsState.value.toList(),
            viewModel = viewModel,
            eventType = REGION,
            onSelectItem = onSelectItem
        )

        MainScreenCard(
            title = stringResource(id = R.string.subregions),
            list = viewModel.subregionsState.value.toList(),
            viewModel = viewModel,
            eventType = SUBREGION,
            onSelectItem = onSelectItem
        )

        ButtonStandard(
            modifier = Modifier.padding(bottom = 32.dp, top = 16.dp),
            stringRes = R.string.all_countries,
            onCLick = {
                viewModel.filterEvent(ALL, allCountries)
                onSelectItem(allCountries)
            })
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreenCard(
    title: String,
    list: List<String>,
    viewModel: MainViewModel,
    eventType: FilterEventType,
    onSelectItem: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .border(
                width = 2.dp,
                color = colorPrimary,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
            )
            .fillMaxWidth()
            .background(colorWhite)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))

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
                style = TextStyle(
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = colorWhite
                )
            )
        }

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
        ) {
            for (item in list) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorSecondary),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            viewModel.filterEvent(eventType, item)
                            onSelectItem(item)
                        },
                    elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation_value))
                ) {
                    Text(
                        text = item,
                        modifier =
                        Modifier.padding(
                            vertical = 4.dp,
                            horizontal = 12.dp
                        ),
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = colorWhite,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}









