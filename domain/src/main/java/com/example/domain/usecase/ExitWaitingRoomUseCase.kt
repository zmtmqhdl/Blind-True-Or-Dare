package com.example.domain.usecase

import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class ExitWaitingRoomUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
) {
    operator fun invoke() {
        webSocketRepository.close()
    }
}