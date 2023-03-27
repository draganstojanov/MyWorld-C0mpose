package com.draganstojanov.myworld_compose.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.ui.theme.colorElementBack
import com.draganstojanov.myworld_compose.ui.theme.colorWhite

@Composable
fun ButtonStandard(
    stringRes: Int,
    onCLick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.element_height_standard)),
        shape = CircleShape,
        onClick = onCLick,
        colors = ButtonDefaults.buttonColors(backgroundColor = colorElementBack),
        elevation = ButtonDefaults.elevation(defaultElevation = dimensionResource(id = R.dimen.elevation_value))
    ) {
        Text(
            stringResource(id = stringRes),
            color = colorWhite, fontSize = 20.sp
        )
    }
}

