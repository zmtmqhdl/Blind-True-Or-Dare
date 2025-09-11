package com.example.domain.repository

import com.example.domain.Event
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UiFlowRepository {
    val loading: StateFlow<Boolean>
    val event: SharedFlow<Event>

    fun show()
    suspend fun hide()
    fun superHide()
    suspend fun emitEvent(event: Event)
}