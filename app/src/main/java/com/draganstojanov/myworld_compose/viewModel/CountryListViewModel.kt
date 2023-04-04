package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.draganstojanov.myworld_compose.model.main.Country
import com.draganstojanov.myworld_compose.model.main.Name
import com.draganstojanov.myworld_compose.model.main.Native
import com.draganstojanov.myworld_compose.model.main.NativeName
import com.draganstojanov.myworld_compose.util.ARG_FILTERED_LIST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var filteredCountryList = listOf<Country>()
    val searchFilteredList: MutableState<List<Country>> = mutableStateOf(emptyList())
    val nativeNamesList: MutableState<List<Native>> = mutableStateOf(emptyList())

    init {
        setFilteredList()
    }

    private fun setFilteredList() {
        val argList: String? = savedStateHandle[ARG_FILTERED_LIST]
        val fl = argList?.replace("*#=@*", "/")
        filteredCountryList = Json.decodeFromString(fl.toString())
        searchFilter("")
    }

    fun searchFilter(phrase: String) {
        val searchList = mutableListOf<Country>()
        searchList.addAll(filteredCountryList.filter {
            containsInName(phrase, it.name) || containsInNativeName(phrase, it.name?.nativeName)
        })
        searchFilteredList.value = searchList
    }

    private fun containsInName(searchFor: String, name: Name?): Boolean =
        name?.common?.contains(searchFor, true) == true || name?.official?.contains(searchFor, true) == true


    private fun containsInNativeName(searchFor: String, nativeName: NativeName?): Boolean {
        for (property in NativeName::class.memberProperties) {
            if (nativeName != null) {
                val native = property.get(nativeName) as Native?
                if (native?.common?.contains(searchFor, true) == true || native?.official?.contains(searchFor, true) == true) {
                    return true
                }
            }
        }
        return false
    }

    fun getNativeNamesList(country: Country?) {
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

