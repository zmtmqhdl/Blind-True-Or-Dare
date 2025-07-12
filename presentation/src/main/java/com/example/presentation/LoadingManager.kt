package com.example.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.concurrent.atomic.AtomicInteger

object LoadingManager {
    private val loadingCount = AtomicInteger(0)
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun show() {
        if (loadingCount.incrementAndGet() == 1) {
            _loading.value = true
        }
    }

    fun hide() {
        if (loadingCount.decrementAndGet() == 0) {
            _loading.value = false
        }
    }
}