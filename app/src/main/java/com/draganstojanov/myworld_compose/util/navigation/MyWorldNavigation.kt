package com.draganstojanov.myworld_compose.util.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.draganstojanov.myworld_compose.screens.MainScreen
import com.draganstojanov.myworld_compose.screens.MapScreen
import com.draganstojanov.myworld_compose.screens.countryDetails.CountryDetailsScreen
import com.draganstojanov.myworld_compose.screens.countryList.CountryListScreen
import java.net.URLDecoder

@Composable
fun MyWorldNavigation() {
    val navController = rememberNavController()
    val onBackPressed = { navController.navigateUp() }
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen
    ) {
        composable<Screen.MainScreen> {
            MainScreen(
                onBackPressed = { onBackPressed() },
                onNavigateToCountryListScreen = { filteredList: String?, title: String? ->
                    navController.navigate(
                        Screen.CountryListScreen(
                            filteredList = filteredList,
                            title = title
                        )
                    )
                },
                hiltViewModel()
            )
        }

        composable<Screen.CountryListScreen> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<Screen.CountryListScreen>()
            CountryListScreen(
                args.title,
                hiltViewModel(),
                onCountryDetails = { countryId -> navController.navigate(Screen.CountryDetailsScreen(countryId)) },
                onBackPressed = { onBackPressed() },

                )
        }

        composable<Screen.CountryDetailsScreen> {
            CountryDetailsScreen(
                onOpenMap = { title, mapUrl -> navController.navigate(Screen.MapScreen(title, mapUrl)) },
                onBackPressed = { navController.popBackStack() },
                hiltViewModel()
            )
        }

        composable<Screen.MapScreen> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<Screen.MapScreen>()
            MapScreen(
                onBackPressed = { onBackPressed() },
                title = URLDecoder.decode(args.title, "UTF8"),
                mapUrl = URLDecoder.decode(args.mapUrl, "UTF8")
            )
        }

    }
}



