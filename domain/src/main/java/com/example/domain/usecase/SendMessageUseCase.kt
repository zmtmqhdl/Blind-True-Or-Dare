package com.example.domain.usecase

import com.example.domain.model.Message
import com.example.domain.model.MessageType
import com.example.domain.repository.RoomRepository
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
    private val roomRepository: RoomRepository

) {
    operator fun invoke(messageType: MessageType) {
        val senderId = roomRepository.player.value!!.playerId
        val message = when(messageType) {
            MessageType.SEND_START -> {
                Message(
                    type = MessageType.SEND_START,
                    senderId = senderId,
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