package com.draganstojanov.myworld_compose.model.main

@kotlinx.serialization.Serializable
data class Currency(
    val symbol: String? = null,
    val name: String? = null,
    var code: String? = null
)
