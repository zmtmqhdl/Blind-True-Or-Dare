package com.example.domain.repository

import com.example.domain.WebSocketStatus
import com.example.domain.model.Message
import com.example.domain.model.MessageType
import com.example.domain.model.Player
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import java.sql.Timestamp

interface WebSocketRepository {
    val message: SharedFlow<Message?>
    val webSocketConnect: SharedFlow<WebSocketStatus>
    val isConnected: StateFlow<Boolean>


    fun connect(
        roomId: String,
        player: Player
    )

    fun send(
        messageType: MessageType,
        playerId: String,
        data: Any?,
        timestamp: Long
    )

    fun close()

    fun reconnect(
        roomId: String,
        player: Player
    )
}