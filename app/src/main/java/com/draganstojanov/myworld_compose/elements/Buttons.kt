package com.draganstojanov.myworld_compose.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.draganstojanov.myworld_compose.R

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
    ) {
        Text(stringResource(id = stringRes))
    }
}
