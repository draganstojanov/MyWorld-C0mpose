package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.model.Name
import com.draganstojanov.myworld_compose.model.Native
import com.draganstojanov.myworld_compose.model.NativeName
import kotlin.reflect.full.memberProperties


class CountryListViewModel : ViewModel() {

    val filteredCountryList: MutableState<List<Country>> = mutableStateOf(emptyList())
    val searchFilteredList: MutableState<List<Country>> = mutableStateOf(emptyList())

    fun searchFilter(phrase: String) {
        val searchList = mutableListOf<Country>()
        searchList.addAll(filteredCountryList.value.filter {
            containsInName(phrase, it.name) || containsInNativeName(phrase, it.name?.nativeName)
        })
        searchFilteredList.value = searchList
    }

    private fun containsInName(searchFor: String, name: Name?): Boolean =
        name?.common?.contains(searchFor, true) == true || name?.official?.contains(searchFor, true) == true


    private fun containsInNativeName(searchFor: String, nativeName: NativeName?): Boolean {
        for (property in NativeName::class.memberProperties) {
            if (nativeName != null) {
                val value = property.get(nativeName) as Native?
                if (value?.common?.contains(searchFor, true) == true || value?.official?.contains(searchFor, true) == true) {
                    return true
                }
            }
        }
        return false
    }

}