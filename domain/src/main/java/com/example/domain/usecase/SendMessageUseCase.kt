package com.example.domain.usecase

import com.example.domain.model.Message
import com.example.domain.model.MessageType
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository
) {
    operator fun invoke(messageType: MessageType) {
        val message = when(messageType) {
            MessageType.SEND_START -> {
                Message(
                    type = MessageType.SEND_START,
                    timestamp = System.currentTimeMillis()
                )
            }
            else -> null
        }
        message?.let {
            webSocketRepository.send(message = it)
        }
    }
}