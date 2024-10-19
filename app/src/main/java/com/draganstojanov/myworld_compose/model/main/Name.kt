package com.draganstojanov.myworld_compose.model.main

@kotlinx.serialization.Serializable
data class Name(
    val nativeName: NativeName? = null,
    val common: String? = null,
    val official: String? = null
)
