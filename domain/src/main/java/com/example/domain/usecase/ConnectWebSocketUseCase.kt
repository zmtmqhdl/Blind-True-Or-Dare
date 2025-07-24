package com.example.domain.usecase

import com.example.domain.model.Player
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class ConnectWebSocketUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository
) {
    operator fun invoke(
        roomId: String,
        player: Player
    ) {
        webSocketRepository.connect(
            roomId = roomId,
            player = player
        )
    }
}