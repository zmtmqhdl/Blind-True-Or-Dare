package com.example.domain.usecase

import com.example.domain.model.Player
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class ConnectWebSocketUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository
) {
    operator fun invoke(
        waitingRoomId: String,
        player: Player
    ) {
        webSocketRepository.connect(
            waitingRoomId = waitingRoomId,
            player = player
        )
    }
}