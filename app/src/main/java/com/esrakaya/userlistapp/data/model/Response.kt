package com.esrakaya.userlistapp.data.model

sealed class Response<out T> {
    data class FetchResponse<T>(
        val data: List<T>,
        val next: String?
    ) : Response<T>()

    data class FetchError(val errorDescription: String) : Response<Nothing>()
}
