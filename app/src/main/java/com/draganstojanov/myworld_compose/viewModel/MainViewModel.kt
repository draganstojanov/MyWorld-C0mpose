package com.draganstojanov.myworld_compose.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.simula.infinity.util.debug.debugLog
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.repository.MyWorldRepository
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
        val sub = mutableSetOf<String>()
        val con = mutableSetOf<String>()
        list.forEach {
            reg.add(it.region.toString())
            sub.add(it.subregion.toString())
            it.continents?.forEach { continent -> con.add(continent.toString()) }

            if (it.subregion == null) {
                debugLog("XXX",msg = "NULL")
            }
            if (it.subregion?.isEmpty() == true) {
                debugLog("XXX",msg = "EMPTY")
            }

        }

        //todo

        debugLog("REG", reg)
        debugLog("SUB", sub)
        debugLog("CON", con)

        debugLog("COUNT", list.size)
        var rc = 0
        reg.forEach { reg ->
            val c = list.count { it.region == reg }
            debugLog(reg, c)
            rc += c
        }
        debugLog("REG-COUNT", rc)

        var sc = 0
        sub.forEach { sub ->
            val c = list.count { it.subregion == sub }
            debugLog(sub, c)
            sc += c
        }
        debugLog("SUB-COUNT", sc)

        var cc = 0
        con.forEach { con ->
            val c = list.count { it.continents?.contains(con) == true }
            debugLog(con, c)
            cc += c
        }
        debugLog("CON-COUNT", cc)

    }

}