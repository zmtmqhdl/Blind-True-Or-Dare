package com.example.blindTrueOrDare.manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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