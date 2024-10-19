package com.draganstojanov.myworld_compose.model.main

@kotlinx.serialization.Serializable
data class Maps(
    val openStreetMaps: String? = null,
    val googleMaps: String? = null
)