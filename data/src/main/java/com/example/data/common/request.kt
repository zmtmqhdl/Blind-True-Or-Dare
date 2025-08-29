package com.example.data.common

import com.example.domain.common.ApiResponse

suspend fun <T> request(block: suspend () -> T): ApiResponse<T> =
    runCatching { block() }
        .fold(
            onSuccess = { ApiResponse.Success(it) },
            onFailure = { ApiResponse.Error(it) }
        )