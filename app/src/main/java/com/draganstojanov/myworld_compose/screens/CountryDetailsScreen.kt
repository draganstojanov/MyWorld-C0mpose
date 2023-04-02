package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.model.Native
import com.draganstojanov.myworld_compose.ui.theme.colorGreyPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.viewModel.CountryDetailsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CountryDetailsScreen(
    navController: NavHostController,
    viewModel: CountryDetailsViewModel
) {
    val countryState = viewModel.countryState
    val nativeNamesState = viewModel.nativeNamesList

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = countryState.value?.name?.common,
                navController = navController
            )
        }
    ) {
        CountryDetails(countryState, nativeNamesState)
    }

}

@Composable
fun CountryDetails(countryState: MutableState<Country?>, nativeNamesState: MutableState<List<Native>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.padding_horizontal))
    ) {
        NameBlock(countryState, nativeNamesState)
        FlagAndCoatOfArms(countryState)
        GeoData(countryState)
    }
}

@Composable
fun GeoData(countryState: MutableState<Country?>) {
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
        DataRowList(stringResource(id = R.string.borders), countryState.value?.borders)//todo lista flag+name
    }
}


@Composable
fun DataRow(label: String?, value: String?) {
    Row(modifier = Modifier.padding(top = 4.dp)) {
        if (label != null) {
            DataLabel(label, Modifier.weight(3f))
        }
        if (value != null) {
            DataValue(value, Modifier.weight(7f))
        }
    }
}


@Composable
fun DataRowList(label: String?, value: List<String?>?) {
    Row(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        if (label != null) {
            DataLabel(label, Modifier.weight(3f))
        }
        if (value != null) {
            DataList(value, Modifier.weight(7f))
        }
    }
}

@Composable
fun DataLabel(label: String, modifier: Modifier = Modifier) {
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


@Composable
fun NameBlock(countryState: MutableState<Country?>, nativeNamesState: MutableState<List<Native>>) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .fillMaxWidth()
            .background(colorSecondary)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            text = countryState.value?.name?.common.toString(),
            style = MaterialTheme.typography.h5,
            color = colorWhite,
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = countryState.value?.name?.official.toString(),
            style = MaterialTheme.typography.body1,
            color = colorWhite,
        )
        Divider(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = colorGreyPrimary,
        )
        repeat(nativeNamesState.value.size) {
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp),
                text = nativeNamesState.value[it].common.toString(),
                style = MaterialTheme.typography.h6,
                color = colorWhite,
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = nativeNamesState.value[it].official.toString(),
                style = MaterialTheme.typography.body2,
                color = colorWhite,
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}

@Composable
fun FlagAndCoatOfArms(countryState: MutableState<Country?>) {

    ConstraintLayout(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            .fillMaxWidth()
            .background(colorGreyPrimary)

    ) {
        val (flag, coatOfArms) = createRefs()
        AsyncImage(
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(flag) {
                    start.linkTo(parent.start)
                    top.linkTo(coatOfArms.top)
                    bottom.linkTo(coatOfArms.bottom)
                    end.linkTo(coatOfArms.start)
                    width = Dimension.fillToConstraints
                },
            model = countryState.value?.flags?.png,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Flag",
            contentScale = ContentScale.FillWidth

        )
        AsyncImage(
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(coatOfArms) {
                    start.linkTo(flag.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            model = countryState.value?.coatOfArms?.png,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "CoatOfArms",
            contentScale = ContentScale.FillWidth,
        )
    }
}


