package com.draganstojanov.myworld_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.draganstojanov.myworld_compose.ui.theme.MyWorldComposeTheme
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.util.navigation.MyWorldNavigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWorldComposeTheme {

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        colorPrimary.toArgb(),
                        colorPrimary.toArgb()
                    ),
                    navigationBarStyle = SystemBarStyle.auto(
                        colorPrimary.toArgb(),
                        colorPrimary.toArgb()
                    )
                )

                window.apply {
                    WindowCompat.getInsetsController(this, this.decorView).apply {
                        isAppearanceLightStatusBars = false
                        isAppearanceLightNavigationBars = false
                    }
                }
                MyWorldNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyWorldComposeTheme {
        MyWorldNavigation()
    }
}