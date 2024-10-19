package com.draganstojanov.myworld_compose.screens.countryDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.ui.theme.colorWhite

@Composable
fun DataRow(label: String?, value: String?, additionalText: String? = "") {
    Row(modifier = Modifier.padding(top = 4.dp)) {
        if (!value.isNullOrEmpty()) {
            DataLabel(label, Modifier.weight(3f))
            DataValue("$value $additionalText", Modifier.weight(7f))
        }
    }
}


@Composable
fun DataRowList(label: String?, value: List<String?>?) {
    Row(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        if (value?.isNotEmpty() == true) {
            DataLabel(label, Modifier.weight(3f))
            DataList(value, Modifier.weight(7f))
        }
    }
}

@Composable
fun DataLabel(label: String?, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            text = if (label.isNullOrEmpty()) "" else "${label}:",
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
