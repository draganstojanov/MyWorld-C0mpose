package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
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


@Composable
fun DataRow(label: String?, value: String?) {
    Row(modifier = Modifier.padding(top = 4.dp)) {
        if (!value.isNullOrEmpty()) {
            DataLabel(label, Modifier.weight(3f))
            DataValue(value, Modifier.weight(7f))
        }
    }
}


@Composable
fun DataRowList(label: String?, value: List<String?>?) {
    Row(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        if (value?.isNotEmpty() == true) {
            DataLabel(label, Modifier.weight(3f))
            DataList(value, Modifier.weight(7f))
        }
    }
}

@Composable
fun DataLabel(label: String?, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            text = "${label}:",
            style = TextStyle(
                color = colorWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Composable
fun DataValue(value: String, modifier: Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = value,
            style = TextStyle(
                color = colorWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
    }
}

@Composable
fun DataList(list: List<String?>?, modifier: Modifier) {
    Column(modifier = modifier) {
        list?.size?.let {
            repeat(it) { index ->
                Box() {
                    list[index]?.let { value ->
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = value,
                            style = TextStyle(
                                color = colorWhite,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                    }
                }
            }
        }
    }
}
