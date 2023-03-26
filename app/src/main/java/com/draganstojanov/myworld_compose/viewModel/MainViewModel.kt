package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.simula.infinity.util.debug.debugLog
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.model.Name
import com.draganstojanov.myworld_compose.model.Native
import com.draganstojanov.myworld_compose.model.NativeName
import com.draganstojanov.myworld_compose.repository.MyWorldRepository
import com.draganstojanov.myworld_compose.util.INDEFINITE
import com.draganstojanov.myworld_compose.util.eventModel.FilterEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MyWorldRepository) : ViewModel() {

    val countriesState: MutableState<List<Country>> = mutableStateOf(emptyList())
    val regionsState: MutableState<Set<String>> = mutableStateOf(emptySet())
    val subregionsState: MutableState<Set<String>> = mutableStateOf(emptySet())
    val continentsState: MutableState<Set<String>> = mutableStateOf(emptySet())

    val filteredCountryList: MutableState<List<Country>> = mutableStateOf(emptyList())


    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repo.getAllCountries()
            countriesState.value = list
            setSublists(list)
        }
    }

    private fun setSublists(list: List<Country>) {
        val reg = mutableSetOf<String>()
        var sub = mutableSetOf<String>()
        val con = mutableSetOf<String>()
        list.forEach {
            reg.add(it.region.toString())
            if (it.subregion.isNullOrEmpty()) {
                it.subregion = INDEFINITE
            }
            sub.add(it.subregion.toString())
            it.continents?.forEach { continent -> con.add(continent.toString()) }
        }

        sub = sub.sortedBy { it }.toMutableSet()
        sub.apply {
            remove(INDEFINITE)
            add(INDEFINITE)
        }

        subregionsState.value = sub.toSet()
        regionsState.value = reg.sorted().toSet()
        continentsState.value = con.sorted().toSet()

    }

    fun filterEvent(filter: FilterEvent) {
        val filteredList = mutableListOf<Country>()

        when (filter) {
            is FilterEvent.All -> filteredList.addAll(countriesState.value)
            is FilterEvent.Continent -> filteredList.addAll(countriesState.value.filter { it.continents?.contains(filter.continent) == true })
            is FilterEvent.Region -> filteredList.addAll(countriesState.value.filter { it.region == filter.region })
            is FilterEvent.Subregion -> filteredList.addAll(countriesState.value.filter { it.subregion == filter.subregion })
            is FilterEvent.Country -> filteredList.addAll(countriesState.value.filter { it.equals(filter.country) })
            is FilterEvent.Search -> filteredList.addAll(countriesState.value.filter {
                containsInName(filter.searchFor, it.name) || containsInNativeName(filter.searchFor, it.name?.nativeName)
            })
        }

        filteredCountryList.value = filteredList.toList()

        debugLog(msg=filteredList.size)

    }

    private fun containsInName(searchFor: String, name: Name?): Boolean =
        name?.common?.contains(searchFor) == true || name?.official?.contains(searchFor) == true


    private fun containsInNativeName(searchFor: String, nativeName: NativeName?): Boolean {
        for (property in NativeName::class.memberProperties) {
            val value = property.get(nativeName!!) as Native
            if (value.common?.contains(searchFor) == true || value.official?.contains(searchFor) == true) {
                return true
            }
        }
        return false
    }

}