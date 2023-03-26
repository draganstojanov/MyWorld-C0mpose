package com.draganstojanov.myworld_compose.util.eventModel

import com.draganstojanov.myworld_compose.model.Country

sealed class FilterEvent {
    object All : FilterEvent()
    data class Search(val searchFor: String) : FilterEvent()
    data class Continent(val continent: String) : FilterEvent()
    data class Region(val region: String) : FilterEvent()
    data class Subregion(val subregion: String) : FilterEvent()
    data class Country(val country: Country?) : FilterEvent()
}
