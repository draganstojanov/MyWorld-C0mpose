package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.draganstojanov.myworld_compose.elements.CustomTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen(
    navController: NavHostController,
    title: String?,
    mapUrl: String?
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                navController = navController
            )
        },
        content = {
            MapsView(mapUrl)
        }
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MapsView(mapUrl: String?) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(mapUrl.toString())
            }
        }, update = {
            it.loadUrl(mapUrl.toString())
        })
    }


}