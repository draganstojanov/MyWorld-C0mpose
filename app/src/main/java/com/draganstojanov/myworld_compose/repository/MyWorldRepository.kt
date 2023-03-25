package com.draganstojanov.myworld_compose.repository

import android.util.Log
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.util.Prefs
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

            val response = networkCall { myWorldApi.getAllCountries() }

            Log.d("ADF-1", response.toString())

            when (response) {
                is ResponseState.Success -> {
                    val countries = (response.data) as List<Country>
                    prefs.saveAllCountries(countries)
                    countries
                }
                is ResponseState.Error -> prefs.getAllCountries()
            }

        } else {
            prefs.getAllCountries()
        }

    }

}