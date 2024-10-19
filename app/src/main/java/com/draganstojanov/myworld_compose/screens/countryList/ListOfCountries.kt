package com.draganstojanov.myworld_compose.screens.countryList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.viewModel.CountryListViewModel


@Composable
fun ListOfCountries(
    viewModel: CountryListViewModel,
    countryList: MutableState<List<Country>>,
    onCountrySelected: (Int) -> Unit
) {
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = countryList.value) { country ->
                CountryItem(
                    viewModel = viewModel,
                    country = country,
                    onCountrySelected = onCountrySelected
                )
            }
        }
    }
}

@Composable
fun CountryItem(
    country: Country,
    viewModel: CountryListViewModel,
    onCountrySelected: (Int) -> Unit
) {
    val nativeNamesList = viewModel.nativeNamesList
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .background(colorSecondary)
            .clickable { country.countryId?.let { onCountrySelected.invoke(it) } }

    ) {
        val (flagIcon, name, nativeName) = createRefs()

        Text(
            text = country.flag.toString(),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .constrainAs(flagIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )

        Text(
            text = country.name?.common.toString(),
            color = colorWhite,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(top = 12.dp)
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(flagIcon.end)
                    end.linkTo(parent.end)
                    baseline.linkTo(flagIcon.baseline)
                    width = Dimension.fillToConstraints
                }
        )

        Column(modifier = Modifier
            .padding(bottom = 8.dp)
            .constrainAs(nativeName) {
                top.linkTo(name.bottom)
                start.linkTo(flagIcon.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }) {
            viewModel.getNativeNamesList(country)
            repeat(nativeNamesList.value.size) {
                Text(
                    text = nativeNamesList.value[it].common.toString(),
                    color = colorWhite,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
