package com.draganstojanov.myworld_compose.model.main

@kotlinx.serialization.Serializable
data class Car(
    val side: String? = null,
    val signs: List<String?>? = null
)
