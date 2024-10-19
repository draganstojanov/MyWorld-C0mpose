package com.draganstojanov.myworld_compose.screens

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.draganstojanov.myworld_compose.composables.CustomTopAppBar

@Composable
fun MapScreen(
    title: String?,
    mapUrl: String?,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                onBackPressed = { onBackPressed() }
            )
        }
    ) {
        MapsView(
            contentPadding = it,
            mapUrl
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MapsView(
    contentPadding: PaddingValues,
    mapUrl: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
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