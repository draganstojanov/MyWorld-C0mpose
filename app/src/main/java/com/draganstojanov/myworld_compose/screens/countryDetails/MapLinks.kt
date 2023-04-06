package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite

@Composable
fun MapLinks(
    googleMaps: () -> Unit,
    openStreetMaps: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        MapLink(stringResource(id = R.string.google_maps), googleMaps)
        MapLink(stringResource(id = R.string.open_street_maps), openStreetMaps)
    }
}


@Composable
fun MapLink(title: String, func: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(CircleShape)
            .background(colorSecondary)
            .clickable {
                func.invoke()
            },
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 12.dp
            ),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorWhite,
                textAlign = TextAlign.Center,
            )
        )
    }
}