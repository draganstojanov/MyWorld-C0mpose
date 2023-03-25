package com.draganstojanov.myworld_compose.util.network


sealed class ResponseState {
    data class Success(val data: Any?) : ResponseState()
    data class Error(val error: String?, val code: Int = 0) : ResponseState()
}