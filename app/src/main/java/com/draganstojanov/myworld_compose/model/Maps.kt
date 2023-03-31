package com.draganstojanov.myworld_compose.model
@kotlinx.serialization.Serializable
data class Maps(
    val openStreetMaps: String? = null,
    val googleMaps: String? = null
)