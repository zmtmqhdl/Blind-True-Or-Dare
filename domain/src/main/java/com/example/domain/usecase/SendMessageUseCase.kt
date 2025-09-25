package com.example.domain.usecase

import com.example.domain.model.MessageType
import com.example.domain.repository.RoomRepository
import com.example.domain.repository.WebSocketRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        messageType: MessageType,
        data: Any? = null
    ) {
        webSocketRepository.send(
            messageType = messageType,
            player = roomRepository.player.value!!,
            data = data,
        )
    }
}