package com.draganstojanov.myworld_compose.util.network.api

import com.draganstojanov.myworld_compose.model.Country
import com.draganstojanov.myworld_compose.util.GET_ALL
import retrofit2.Response
import retrofit2.http.GET

interface MyWorldApi {

    @GET(GET_ALL)
    suspend fun getAllCountries(): Response<List<Country>>


}