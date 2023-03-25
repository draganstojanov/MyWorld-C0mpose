package com.draganstojanov.myworld_compose.model
@kotlinx.serialization.Serializable
data class Idd(
    val suffixes: List<String?>? = null,
    val root: String? = null
)
