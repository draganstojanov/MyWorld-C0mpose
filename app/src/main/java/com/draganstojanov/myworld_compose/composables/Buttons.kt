package com.draganstojanov.myworld_compose.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite

@Composable
fun ButtonStandard(
    modifier: Modifier,
    stringRes: Int,
    onCLick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.element_height_standard)),
        shape = CircleShape,
        onClick = onCLick,
        colors = ButtonDefaults.buttonColors(containerColor = colorPrimary),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = dimensionResource(id = R.dimen.elevation_value))
    ) {
        Text(
            stringResource(id = stringRes),
            style = TextStyle(
                color = colorWhite,
                fontSize = 20.sp
            )

        )
    }
}

