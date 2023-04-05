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
fun InfoData(
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
        if (!countryState.value?.capital.isNullOrEmpty()) {
            DataRowList(stringResource(id = R.string.capital), countryState.value?.capital)
        }
        DataRow(stringResource(id = R.string.area), countryState.value?.area.toString(), "km\u00B2")
        DataRow(stringResource(id = R.string.population), countryState.value?.population.toString())
        if (countryState.value?.languages != null) {
            DataRowList(stringResource(id = R.string.languages), viewModel.getLanguageList(countryState.value))
        }
        if (countryState.value?.currencies != null) {
            val currencies = viewModel.getCurrencyList(countryState.value)
            repeat(currencies.size) { index ->
                val label = if (index == 0) stringResource(id = R.string.currencies) else null
                val code = "${stringResource(id = R.string.code)}: ${currencies[index]?.code}\n"
                val name = "${stringResource(id = R.string.name)}: ${currencies[index]?.name}\n"
                val symbol = "${stringResource(id = R.string.symbol)}: ${currencies[index]?.symbol ?: '-'}"

                DataRow(label, code+name+symbol)
            }


        }

//        DataRow(stringResource(id = R.string.subregion), countryState.value?.subregion)
//        DataRowList(stringResource(id = R.string.continent), countryState.value?.continents)
//        if (!countryState.value?.borders.isNullOrEmpty()) {
//            val borderList = viewModel.getBorderList(countryState.value?.borders)
//            DataRowList(stringResource(id = R.string.borders), borderList)
//        }
    }
}