package com.draganstojanov.myworld_compose.model


@kotlinx.serialization.Serializable
data class Country(

    var countryId: Int? = null,

    val region: String? = null,
    var subregion: String? = null,
    val continents: List<String?>? = null,

    val name: Name? = null,
    val altSpellings: List<String?>? = null,

    val cca2: String? = null,
    val cca3: String? = null,
    val ccn3: String? = null,
    val flag: String? = null,

    val car: Car? = null,
    val idd: Idd? = null,
    val tld: List<String?>? = null,

    val fifa: String? = null,
    val cioc: String? = null,

    val flags: Flags? = null,
    val coatOfArms: CoatOfArms? = null,

    val maps: Maps? = null,
    val latlng: List<Double?>? = null,

    val capital: List<String?>? = null,
    val capitalInfo: CapitalInfo? = null,
    val area: Double? = null,
    val population: Int? = null,

    val borders: List<String?>? = null,
    val independent: Boolean? = null,
    val unMember: Boolean? = null,
    val landlocked: Boolean? = null,

    val gini: Gini? = null,
    val currencies: Currencies? = null,
    val languages: Languages? = null,
    val timezones: List<String?>? = null,

    )