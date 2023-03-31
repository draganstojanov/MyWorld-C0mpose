package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.viewModel.CountryListViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryListScreen(
    navController: NavHostController,
    filteredList: String?,
    title: String?
) {
    val viewModel: CountryListViewModel = viewModel()
    val fl = filteredList?.replace("*#=@*", "/")
    viewModel.filteredCountryList.value = Json.decodeFromString(fl.toString())
    viewModel.searchFilter("")
    val countryList = viewModel.searchFilteredList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title.toString(), color = colorWhite) },
                backgroundColor = colorPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            null,
                            tint = colorWhite,
                        )
                    }
                }
            )
        }
    ) {
        CountriesListLayout(
            viewModel = viewModel,
            countryList = countryList,
        )
    }
}

@Composable
fun CountriesListLayout(
    viewModel: CountryListViewModel,
    countryList: MutableState<List<Country>>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal))
    ) {
        SearchTextField(viewModel = viewModel)
        ListOfCountries(viewModel, countryList)
    }
}

@Composable
fun SearchTextField(viewModel: CountryListViewModel) {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier.fillMaxWidth()
    )
    {
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                viewModel.searchFilter(it.text)
            },
            label = { Text(stringResource(id = R.string.search_hint)) },
            placeholder = { Text(stringResource(id = R.string.search_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 20.sp),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    inputValue.value = TextFieldValue("")
                    viewModel.searchFilter("")
                }) {
                    Icon(
                        Icons.Filled.Close,
                        null,
                        tint = colorPrimary,
                    )
                }
            },
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorPrimary,
                trailingIconColor = colorPrimary,
                cursorColor = colorSecondary,
                backgroundColor = colorWhite,
                focusedIndicatorColor = colorPrimary,
                unfocusedIndicatorColor = colorPrimary,
                focusedLabelColor = colorSecondary,
                unfocusedLabelColor = colorSecondary,
                placeholderColor = colorSecondary
            )
        )
    }
}


@Composable
fun ListOfCountries(
    viewModel: CountryListViewModel,
    countryList: MutableState<List<Country>>
) {
    Box() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = countryList.value) { country ->
                CountryItem(viewModel = viewModel, country = country)
            }
        }
    }
}


@Composable
fun CountryItem(
    country: Country,
    viewModel: CountryListViewModel
) {
    val nativeNamesList = viewModel.nativeNamesList
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .background(colorSecondary),
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
            style = MaterialTheme.typography.h6,
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
                    style = MaterialTheme.typography.body1,
                )
            }
        }


    }
}



