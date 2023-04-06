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
                DataRow(label, code + name + symbol)
            }
        }
        if (countryState.value?.timezones != null) {
            DataRowList(stringResource(id = R.string.time_zones), countryState.value?.timezones)
        }
        DataRow(
            stringResource(id = R.string.independent),
            if (countryState.value?.independent == true) stringResource(id = R.string.yes) else stringResource(id = R.string.no)
        )
        DataRow(
            stringResource(id = R.string.un_member),
            if (countryState.value?.unMember == true) stringResource(id = R.string.yes) else stringResource(id = R.string.no)
        )
        DataRow(
            stringResource(id = R.string.landlocked),
            if (countryState.value?.landlocked == true) stringResource(id = R.string.yes) else stringResource(id = R.string.no)
        )
        DataRow(stringResource(id = R.string.cca2), countryState.value?.cca2)
        DataRow(stringResource(id = R.string.cca3), countryState.value?.cca3)
        DataRow(stringResource(id = R.string.ccn3), countryState.value?.ccn3.toString())
        if (countryState.value?.tld != null) {
            DataRowList(stringResource(id = R.string.tld), countryState.value?.tld)
        }

        if (countryState.value?.idd != null) {
            if (countryState.value?.idd?.suffixes != null)
                if (countryState.value?.idd?.suffixes?.size!! < 6) {
                    repeat(countryState.value?.idd?.suffixes?.size!!) { index ->
                        val label = if (index == 0) stringResource(id = R.string.calling_codes) else null
                        val root = "${countryState.value?.idd?.root}"
                        val suffix = "${countryState.value?.idd?.suffixes!![index]}"
                        DataRow(label, root + suffix)
                    }
                } else {
                    DataRow(stringResource(id = R.string.calling_codes), "${countryState.value?.idd?.root}")
                }

        }

        if (countryState.value?.car != null) {
            if (countryState.value?.car?.signs != null) {
                DataRowList(stringResource(id = R.string.car_signs), countryState.value?.car?.signs)
            }
        }

        DataRow(stringResource(id = R.string.fifa), countryState.value?.fifa)
        DataRow(stringResource(id = R.string.ioc), countryState.value?.cioc)

    }

}
