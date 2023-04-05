package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.viewModel.CountryDetailsViewModel

@Composable
fun GeoData(
    countryState: MutableState<Country?>,
    viewModel: CountryDetailsViewModel
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .fillMaxWidth()
            .background(colorSecondary)
            .padding(top = 4.dp, bottom = 8.dp)
    ) {
        DataRow(stringResource(id = R.string.region), countryState.value?.region)
        DataRow(stringResource(id = R.string.subregion), countryState.value?.subregion)
        DataRowList(stringResource(id = R.string.continent), countryState.value?.continents)
        if (!countryState.value?.borders.isNullOrEmpty()) {
            val borderList = viewModel.getBorderList(countryState.value?.borders)
            DataRowList(stringResource(id = R.string.borders), borderList)
        }
    }
}


