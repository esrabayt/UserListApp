package com.esrakaya.userlistapp.domain.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

inline val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

inline val <T> Result<T>.errorMessage: String?
    get() = (this as? Result.Error)?.message

inline fun <T> Result<T>.onSuccess(block: (data: T) -> Unit): Result<T> = also {
    data?.let { block(it) }
}

inline fun <T> Result<T>.onError(block: (String) -> Unit): Result<T> = also {
    errorMessage?.let { block(it) }
}
