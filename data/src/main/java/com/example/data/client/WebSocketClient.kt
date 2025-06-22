package com.example.data.client

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketClient(
    private val waitingRoomId: String,
    private val playerId: String,
    private val listener: WebSocketListener
) {
    private val request = Request.Builder()
        .url("ws://10.0.2.2:8080/game?roomId=$waitingRoomId&userId=$playerId")
        .build()

    private val client = OkHttpClient()

    private var webSocket: WebSocket? = null

    fun connect() {
        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closing normally")
    }
}
