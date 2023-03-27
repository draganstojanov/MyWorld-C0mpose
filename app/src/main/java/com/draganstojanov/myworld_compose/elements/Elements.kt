package com.draganstojanov.myworld_compose.elements

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R

@Composable
fun SomethingWentWrongFullScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(id = R.string.something_went_wrong),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
        )
    }
}


fun ShowToast(context: Context, message: Any?, isLong: Boolean = false) {
    Toast.makeText(context, message.toString(), if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}