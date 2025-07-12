package com.example.data.repositoryImpl

import android.os.Handler
import android.os.Looper
import com.example.domain.repository.LoadingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadingRepositoryImpl
@Inject
constructor(
) : LoadingRepository {
    private val loadingCount = AtomicInteger(0)
    private val _loading = MutableStateFlow(false)
    override val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private var showTime: Long = 0L
    private val minDurationMillis = 5000L
    private val handler = Handler(Looper.getMainLooper())

    override fun show() {
        if (loadingCount.incrementAndGet() == 1) {
            showTime = System.currentTimeMillis()
            _loading.value = true
        }
    }

    override suspend fun hide() {
        if (loadingCount.decrementAndGet() == 0) {
            val elapsed = System.currentTimeMillis() - showTime
            if (elapsed >= minDurationMillis) {
                _loading.value = false
            } else {
                val delayMillis = minDurationMillis - elapsed
                delay(delayMillis)
                if (loadingCount.get() == 0) {
                    _loading.value = false
                }
            }
        }
    }

    override fun superHide() {
        loadingCount.set(0)
        handler.removeCallbacksAndMessages(null)
        _loading.value = false
    }
}