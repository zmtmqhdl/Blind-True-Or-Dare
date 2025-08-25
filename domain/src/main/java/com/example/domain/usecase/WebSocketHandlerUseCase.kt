package com.example.domain.usecase

import com.example.domain.WebSocketStatus
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class WebSocketHandlerUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository
) {
    suspend operator fun invoke(
        onConnect: (String) -> Unit = {},
        onConnectFailure: (Throwable) -> Unit = {},
        onDisconnect: () -> Unit = {}
    ) {
        webSocketRepository.webSocketConnect.collect {
            when (it) {
                is WebSocketStatus.WebSocketConnectSuccess -> {
                    onConnect(it.roomUrl)
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