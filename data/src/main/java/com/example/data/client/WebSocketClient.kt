package com.example.data.client

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketClient(
    private val waitingRoomId: String,
    private val playerId: String,
    private val onMessage: (String) -> Unit,
    private val onOpen: () -> Unit = {},
    private val onFailure: (Throwable) -> Unit = {}
) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/game?roomId=$waitingRoomId&userId=$playerId")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onOpen()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                onMessage(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onFailure(t)
            }
        })
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closing normally")
    }
}
