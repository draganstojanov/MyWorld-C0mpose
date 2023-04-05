package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.ui.theme.colorGreyPrimary

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
            placeholder = painterResource(id = R.drawable.ic_flag),
            error = painterResource(id = R.drawable.ic_flag),
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
            placeholder = painterResource(id = R.drawable.ic_public),
            error = painterResource(id = R.drawable.ic_public),
            contentDescription = "CoatOfArms",
            contentScale = ContentScale.FillWidth,
        )
    }
}