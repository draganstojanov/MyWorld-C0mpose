package com.draganstojanov.myworld_compose.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.repository.MyWorldRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MyWorldRepository) : ViewModel() {

    val countriesState: MutableState<List<Country>> = mutableStateOf(emptyList<Country>())

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            countriesState.value = repo.getAllCountries()
        }//todo
    }

}