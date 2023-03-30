package com.draganstojanov.myworld_compose.util

import com.draganstojanov.myworld_compose.MyWorldApplication
import com.draganstojanov.myworld_compose.R

//Network
const val VALUE_APPLICATION_JSON = "application/json"
const val BASE_URL = "https://restcountries.com/v3.1/"
const val GET_ALL = "all"

//Lists
val INDEFINITE = MyWorldApplication.instance.getString(R.string.indefinite)//TODO nije dobro

// Navigation bundle
const val ARG_FILTERED_LIST = "argFilteredList"
const val ARG_TITLE = "argTitle"

