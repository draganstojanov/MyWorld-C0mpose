package com.draganstojanov.myworld_compose.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.draganstojanov.myworld_compose.model.main.Country
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Prefs(private val context: Context) {

    companion object {
        private const val PREF_NAME = "MyWorld_Compose.SHARED_PREFERENCES"
        private const val PREFS_ALL_COUNTRIES = "allCountries"
        private const val PREF_LAST_TIMESTAMP = "lastTimestamp"
        private const val TIMESTAMP_GAP = 24 * 60 * 60 * 1000L
    }

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveAllCountries(countries: List<Country>) {
        sharedPreferences.edit { putString(PREFS_ALL_COUNTRIES, Json.encodeToString(countries)) }
    }

    fun getAllCountries(): List<Country> {
        val countries = sharedPreferences.getString(PREFS_ALL_COUNTRIES, null)
        return if (countries != null) Json.decodeFromString(countries) else emptyList()
    }

     fun saveLastTimestamp() {
        sharedPreferences.edit().putLong(PREF_LAST_TIMESTAMP, System.currentTimeMillis()).apply()
    }

    fun lastTimestampCheck(): Boolean = (System.currentTimeMillis() - sharedPreferences.getLong(PREF_LAST_TIMESTAMP, 0L)) > TIMESTAMP_GAP


}