package com.example.domain.repository

import com.example.domain.WebSocketStatus
import com.example.domain.model.Message
import com.example.domain.model.Player
import kotlinx.coroutines.flow.SharedFlow

interface WebSocketRepository {
    val message: SharedFlow<Message?>
    val webSocketConnect: SharedFlow<WebSocketStatus>


    fun connect(
        roomId: String,
        player: Player
    )
    fun send(message: Message)
    fun close()


}