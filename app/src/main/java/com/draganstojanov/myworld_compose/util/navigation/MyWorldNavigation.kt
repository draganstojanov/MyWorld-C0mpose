package com.draganstojanov.myworld_compose.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.draganstojanov.myworld_compose.screens.CountryListScreen
import com.draganstojanov.myworld_compose.screens.MainScreen

@Composable
fun MyWorldNavigation() {
    val navController = rememberNavController()
    val navHost = NavHost(
        navController = navController,
        startDestination = NavScreens.MainScreen.name
    ) {
        composable(NavScreens.MainScreen.name) {
            MainScreen(navController)
        }
        composable(NavScreens.CountryListScreen.name){
            CountryListScreen(navController)
        }
    }
}

