package com.draganstojanov.myworld_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color.Black,
    secondary = Color.DarkGray
)

private val LightColorPalette = lightColorScheme(
    primary = Color.White,
    secondary = Color.LightGray)


@Composable
fun MyWorldComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
      DarkColorPalette
    } else {
      LightColorPalette
    }

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}