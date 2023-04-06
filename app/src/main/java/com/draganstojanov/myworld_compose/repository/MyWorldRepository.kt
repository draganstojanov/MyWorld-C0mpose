package com.draganstojanov.myworld_compose.repository

import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.util.Prefs
import com.draganstojanov.myworld_compose.util.debug.debugLog
import com.draganstojanov.myworld_compose.util.network.ResponseParser
import com.draganstojanov.myworld_compose.util.network.ResponseState
import com.draganstojanov.myworld_compose.util.network.api.MyWorldApi
import javax.inject.Inject

class MyWorldRepository @Inject constructor(
    private val myWorldApi: MyWorldApi,
    private val prefs: Prefs
) : ResponseParser() {

    suspend fun getAllCountries(): List<Country> {

        return if (prefs.lastTimestampCheck()) {
            when (val response = networkCall { myWorldApi.getAllCountries() }) {
                is ResponseState.Success -> {
                    val c = (response.data) as List<*>
                    val countries = c.filterIsInstance<Country>().apply { if (size != c.size) return emptyList() }

                    countries.forEachIndexed { index, country ->
                        country.countryId = index + 1
                    }


                    //TODO TEST
                    countries.forEach { country: Country ->
                        if (country.idd != null) {
                            if (country.idd.suffixes != null) {
                                if (country.idd.suffixes.size > 1) {
                                    debugLog("SUFFIX-CO", country.name?.common)
                                    debugLog("SUFFIX-XX", country.idd.suffixes)
                                }else {
                                   // debugLog("SUFFIX-SI", country.idd.suffixes)
                                }
                            }
                        }
                    }






                    prefs.apply {
                        saveAllCountries(countries)
                        saveLastTimestamp()
                    }
                    countries
                }
                is ResponseState.Error -> prefs.getAllCountries()
            }

        } else {
            prefs.getAllCountries()
        }

    }

}