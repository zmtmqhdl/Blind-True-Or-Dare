package com.example.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface LoadingRepository {
    val loading: StateFlow<Boolean>
    fun show()
    suspend fun hide()
    fun superHide()
}