package com.example.domain.common

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val throwable: Throwable) : ApiResponse<Nothing>()
}

inline fun <T> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) action(data)
    return this
}

inline fun <T> ApiResponse<T>.onFailure(action: (Throwable) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Error) action(throwable)
    return this
}

suspend fun <T> request(block: suspend () -> T): ApiResponse<T> =
    runCatching { block() }
        .fold(
            onSuccess = { ApiResponse.Success(it) },
            onFailure = { ApiResponse.Error(it) }
        )