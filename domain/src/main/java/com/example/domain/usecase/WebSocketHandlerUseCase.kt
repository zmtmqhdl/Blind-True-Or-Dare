package com.example.domain.usecase

import com.example.domain.WebSocketStatus
import com.example.domain.repository.WebSocketRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebSocketHandlerUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository
) {
    private var job: Job? = null

    operator fun invoke(
        viewModelScope: CoroutineScope,
        onConnect: (String) -> Unit = {},
        onConnectFailure: (Throwable) -> Unit = {},
        onDisconnect: () -> Unit = {}
    ) {
        job?.cancel()
        job = viewModelScope.launch {
            webSocketRepository.webSocketConnect.collect {
                when (it) {
                    is WebSocketStatus.WebSocketConnectSuccess -> {
                        onConnect(it.roomId)
                    }
                    is WebSocketStatus.WebSocketConnectFailure -> {
                        onConnectFailure(it.error)
                    }
                    is WebSocketStatus.WebSocketDisconnect -> {
                        onDisconnect()
                    }

                }
            }
        }
    }
}