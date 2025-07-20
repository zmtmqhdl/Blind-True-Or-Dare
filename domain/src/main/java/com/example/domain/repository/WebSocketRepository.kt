package com.example.domain.repository

import com.example.domain.Event
import com.example.domain.WebSocketStatus
import com.example.domain.model.Message
import com.example.domain.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface WebSocketRepository {
    val message: SharedFlow<Message?>
    val webSocketConnect: SharedFlow<WebSocketStatus>


    fun connect(
        waitingRoomId: String,
        player: Player
    )
    fun send(message: String)
    fun close()


}