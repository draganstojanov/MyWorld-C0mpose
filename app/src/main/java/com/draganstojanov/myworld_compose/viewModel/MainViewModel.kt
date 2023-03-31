package com.draganstojanov.myworld_compose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.repository.MyWorldRepository
import com.draganstojanov.myworld_compose.util.INDEFINITE
import com.draganstojanov.myworld_compose.util.constants.FilterEventType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MyWorldRepository) : ViewModel() {

    val countriesState: MutableState<List<Country>> = mutableStateOf(emptyList())
    val regionsState: MutableState<Set<String>> = mutableStateOf(emptySet())
    val subregionsState: MutableState<Set<String>> = mutableStateOf(emptySet())
    val continentsState: MutableState<Set<String>> = mutableStateOf(emptySet())

    val filteredCountryList: MutableState<List<Country>> = mutableStateOf(emptyList())
    var title: String? = null


    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repo.getAllCountries().sortedBy { country -> country.name?.common }
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

    fun filterEvent(eventType: FilterEventType, filter: String) {
        val filteredList = mutableListOf<Country>()
        when (eventType) {
            FilterEventType.ALL -> filteredList.addAll(countriesState.value)
            FilterEventType.CONTINENT -> filteredList.addAll(countriesState.value.filter { it.continents?.contains(filter) == true })
            FilterEventType.REGION -> filteredList.addAll(countriesState.value.filter { it.region == filter })
            FilterEventType.SUBREGION -> filteredList.addAll(countriesState.value.filter { it.subregion == filter })
        }
        title = filter
        filteredCountryList.value = filteredList.toList()
    }

}