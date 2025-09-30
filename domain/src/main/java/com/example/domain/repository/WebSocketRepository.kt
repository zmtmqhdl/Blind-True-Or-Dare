package com.example.domain.repository

import com.example.domain.WebSocketStatus
import com.example.domain.model.Message
import com.example.domain.model.MessageType
import com.example.domain.model.Player
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface WebSocketRepository {
    val message: SharedFlow<Message?>
    val webSocketConnect: SharedFlow<WebSocketStatus>


    fun connect(
        roomId: String,
        player: Player
    )

    fun send(
        messageType: MessageType,
        player: Player,
        data: Any?,
    )

    fun close()

    fun reconnect()
}