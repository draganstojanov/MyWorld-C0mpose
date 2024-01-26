package com.draganstojanov.myworld_compose.util.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.draganstojanov.myworld_compose.screens.MainScreen
import com.draganstojanov.myworld_compose.screens.MapScreen
import com.draganstojanov.myworld_compose.screens.countryDetails.CountryDetailsScreen
import com.draganstojanov.myworld_compose.screens.countryList.CountryListScreen
import com.draganstojanov.myworld_compose.util.constants.ARG_COUNTRY_ID
import com.draganstojanov.myworld_compose.util.constants.ARG_FILTERED_LIST
import com.draganstojanov.myworld_compose.util.constants.ARG_TITLE
import com.draganstojanov.myworld_compose.util.constants.ARG_URL
import java.net.URLDecoder

@Composable
fun MyWorldNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreens.MainScreen.name
    ) {

        composable(NavScreens.MainScreen.name) {
            MainScreen(navController, hiltViewModel())
        }

        composable(
            "${NavScreens.CountryListScreen.name}/{$ARG_FILTERED_LIST}/{$ARG_TITLE}",
            arguments = listOf(
                navArgument(ARG_FILTERED_LIST) { type = NavType.StringType },
                navArgument(ARG_TITLE) { type = NavType.StringType }
            )
        ) {
            CountryListScreen(
                navController,
                it.arguments?.getString(ARG_TITLE),
                hiltViewModel()
            )
        }

        composable(
            "${NavScreens.CountryDetailsScreen.name}/{$ARG_COUNTRY_ID}",
            arguments = listOf(navArgument(ARG_COUNTRY_ID) { type = NavType.IntType })
        ) {
            CountryDetailsScreen(navController, hiltViewModel())
        }

        composable(
            "${NavScreens.MapScreen.name}/{$ARG_TITLE}/{$ARG_URL}",
            arguments = listOf(
                navArgument(ARG_TITLE) { type = NavType.StringType },
                navArgument(ARG_URL) { type = NavType.StringType }
            )
        ) {
            MapScreen(
                navController,
                URLDecoder.decode(it.arguments?.getString(ARG_TITLE), "UTF8"),
                URLDecoder.decode(it.arguments?.getString(ARG_URL), "UTF8")
            )
        }
    }
}

