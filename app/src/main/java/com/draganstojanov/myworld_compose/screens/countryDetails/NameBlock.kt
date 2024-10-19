package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.model.main.Native
import com.draganstojanov.myworld_compose.ui.theme.colorGreyPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite

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
            style = MaterialTheme.typography.titleMedium,
            color = colorWhite,
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = countryState.value?.name?.official.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = colorWhite,
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = colorGreyPrimary
        )
        repeat(nativeNamesState.value.size) {
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp),
                text = nativeNamesState.value[it].common.toString(),
                style = MaterialTheme.typography.titleSmall,
                color = colorWhite,
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = nativeNamesState.value[it].official.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = colorWhite,
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}