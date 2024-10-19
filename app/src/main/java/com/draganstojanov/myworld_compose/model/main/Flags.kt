package com.draganstojanov.myworld_compose.model.main

@kotlinx.serialization.Serializable
data class Flags(
    val svg: String? = null,
    val png: String? = null,
    val alt: String? = null
)
