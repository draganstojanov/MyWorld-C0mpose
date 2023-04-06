package com.draganstojanov.myworld_compose.model.main


@kotlinx.serialization.Serializable
data class Country(

    var countryId: Int? = null,

    val flag: String? = null,
    val name: Name? = null,

    val flags: Flags? = null,
    val coatOfArms: CoatOfArms? = null,

    val region: String? = null,
    var subregion: String? = null,
    val continents: List<String?>? = null,
    val borders: List<String?>? = null,

    val maps: Maps? = null,

    val capital: List<String?>? = null,
    val area: Double? = null,
    val population: Int? = null,

    val languages: Languages? = null,
    val currencies: Currencies? = null,
    val timezones: List<String?>? = null,

    val independent: Boolean? = null,
    val unMember: Boolean? = null,
    val landlocked: Boolean? = null,

    val cca2: String? = null,
    val cca3: String? = null,
    val ccn3: String? = null,

    val tld: List<String?>? = null,
    val idd: Idd? = null,

    val car: Car? = null,
    val fifa: String? = null,
    val cioc: String? = null,


    )