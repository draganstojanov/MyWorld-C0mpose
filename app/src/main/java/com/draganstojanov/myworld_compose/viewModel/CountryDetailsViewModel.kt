package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.draganstojanov.myworld_compose.model.main.*
import com.draganstojanov.myworld_compose.util.constants.ARG_COUNTRY_ID
import com.draganstojanov.myworld_compose.util.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val prefs: Prefs
) : ViewModel() {

    val countryState: MutableState<Country?> = mutableStateOf(null)
    val nativeNamesList: MutableState<List<Native>> = mutableStateOf(emptyList())

    private val allCountries: List<Country> get() = prefs.getAllCountries()


    init {
        getCountry()
    }

    private fun getCountry() {
        val countryId: Int? = savedStateHandle[ARG_COUNTRY_ID]
        val countries = prefs.getAllCountries()
        val country = countries.firstOrNull { it.countryId == countryId }
        countryState.value = country
        getNativeNamesList(country)
    }


    private fun getNativeNamesList(country: Country?) {
        val list = mutableListOf<Native>()
        for (property in NativeName::class.memberProperties) {
            if (country?.name?.nativeName != null) {
                val native: Native? = property.get(country.name.nativeName) as Native?
                if (native != null) {
                    list.add(native)
                }
            }
        }
        nativeNamesList.value = list
    }

    fun getBorderList(borders: List<String?>?): List<String?> {
        val borderList = mutableListOf<String>()
        borders?.forEach { neighbour ->
            val borderCountry = allCountries.firstOrNull { country -> neighbour?.equals(country.cca3, true) == true }
            val borderText = "${borderCountry?.flag} ${borderCountry?.name?.common}"
            borderList.add(borderText)
        }
        return borderList
    }

    fun getLanguageList(country: Country?): List<String?> {
        val langList = mutableListOf<String>()
        if (country?.languages != null) {
            for (property in Languages::class.memberProperties) {
                val lang = property.get(country.languages) as String?
                if (!lang.isNullOrEmpty()) {
                    langList.add(lang)
                }
            }
        }
        return langList
    }

    fun getCurrencyList(country: Country?): List<Currency?> {
        val currencyList = mutableListOf<Currency?>()
        if (country?.currencies != null) {
        for (property in Currencies::class.memberProperties) {
                val currency = property.get(country.currencies) as Currency?
                if (currency != null) {
                    currency.code = property.name
                    currencyList.add(currency)
                }
            }
        }
        return currencyList
    }

}