package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.util.ARG_COUNTRY_ID
import com.draganstojanov.myworld_compose.util.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val prefs: Prefs
) : ViewModel() {

    val countryState: MutableState<Country?> = mutableStateOf(null)

    init {
        getCountry()
    }

    private fun getCountry() {
        val countryId: Int? = savedStateHandle[ARG_COUNTRY_ID]
        val countries = prefs.getAllCountries()
        countryState.value = countries.firstOrNull { it.countryId == countryId }
    }


}