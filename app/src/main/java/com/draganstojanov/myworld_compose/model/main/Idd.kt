package com.draganstojanov.myworld_compose.model.main

@kotlinx.serialization.Serializable
data class Idd(
    val suffixes: List<String?>? = null,
    val root: String? = null
)
