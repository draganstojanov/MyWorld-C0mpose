package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.model.Native
import com.draganstojanov.myworld_compose.model.NativeName
import com.draganstojanov.myworld_compose.util.ARG_COUNTRY_ID
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

    init {
        getCountry()
    }

    private fun getCountry() {
        val countryId: Int? = savedStateHandle[ARG_COUNTRY_ID]
        val countries = prefs.getAllCountries()
        val country=countries.firstOrNull { it.countryId == countryId }
        countryState.value = country
        getNativeNamesList(country)
    }


  private  fun getNativeNamesList(country: Country?) {
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


}