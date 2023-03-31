package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.util.navigation.NavScreens
import com.draganstojanov.myworld_compose.viewModel.CountryListViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryListScreen(
    navController: NavHostController,
 //   filteredList: String?,
    title: String?,
    viewModel: CountryListViewModel
) {
//    val fl = filteredList?.replace("*#=@*", "/")
//    viewModel.filteredCountryList.value = Json.decodeFromString(fl.toString())
//    viewModel.searchFilter("")
    val countryList = viewModel.searchFilteredList

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                navController = navController
            )
        }
    ) {
        CountriesListLayout(
            viewModel = viewModel,
            countryList = countryList
        ) {
            navController.navigate("${NavScreens.CountryDetailsScreen.name}/${it}")
        }
    }
}

@Composable
fun CountriesListLayout(
    viewModel: CountryListViewModel,
    countryList: MutableState<List<Country>>,
    onCountrySelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal))
    ) {
        SearchTextField(viewModel = viewModel)
        ListOfCountries(viewModel, countryList, onCountrySelected)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(viewModel: CountryListViewModel) {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier.fillMaxWidth()
    )
    {
        val keyboardController = LocalSoftwareKeyboardController.current
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
                    keyboardController?.hide()
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



