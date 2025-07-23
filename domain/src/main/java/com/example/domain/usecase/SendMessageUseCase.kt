package com.example.domain.usecase

import android.util.Log
import com.example.domain.model.Message
import com.example.domain.model.MessageType
import com.example.domain.repository.WaitingRoomRepository
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
    private val waitingRoomRepository: WaitingRoomRepository

) {
    operator fun invoke(messageType: MessageType) {
        val senderId = waitingRoomRepository.player.value!!.playerId
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