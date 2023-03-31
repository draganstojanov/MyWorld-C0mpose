package com.draganstojanov.myworld_compose.util.network

import android.util.Log
import com.draganstojanov.myworld_compose.MyWorldApplication
import com.draganstojanov.myworld_compose.util.connectivity.hasNetwork
import retrofit2.Response

open class ResponseParser {

    suspend fun <T> networkCall(networkCall: suspend () -> Response<T>): ResponseState {
        if (MyWorldApplication.instance.hasNetwork()) {
            return try {
                val response = networkCall()
                when (response.code()) {
                    in 200..299 -> ResponseState.Success(response.body())
                    else -> {
                        val errorBody = response.errorBody()
                        val errMsg = errorBody!!.byteStream().bufferedReader().use { it.readText() }
                        ResponseState.Error(error = errMsg, code = response.code())
                    }
                }
            } catch (exc: Exception) {
                ResponseState.Error(exc.localizedMessage)
            }
        } else {
            return ResponseState.Error("Network is unavailable")
        }
    }
}