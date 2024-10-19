package com.draganstojanov.myworld_compose.util.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    object MainScreen

    @Serializable
    data class CountryListScreen(val filteredList: String?, val title: String?)

    @Serializable
    data class CountryDetailsScreen(val countryId:Int)

    @Serializable
    data class MapScreen(val title: String, val mapUrl: String)

}