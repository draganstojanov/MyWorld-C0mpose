package com.draganstojanov.myworld_compose.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String?,
    hasBackButton: Boolean = true,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(title.toString(), color = colorWhite) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorPrimary),
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        null,
                        tint = colorWhite,
                    )
                }
            }
        }
    )
}


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


fun showToast(context: Context, message: Any?, isLong: Boolean = false) {
    Toast.makeText(context, message.toString(), if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}