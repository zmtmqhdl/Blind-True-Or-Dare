package com.example.data.client

import com.example.data.toDto
import com.example.domain.model.Player
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.URLEncoder

object SocketManager {
    private val client: OkHttpClient
        get() = OkHttpClient()
    private var webSocket: WebSocket? = null

    private val listeners = mutableListOf<(String) -> Unit>()

    fun connect(waitingRoomId: String, player: Player, onSuccess: () -> Unit = {}, onFailure: (Throwable) -> Unit = {}) {
        if (webSocket != null) return

        val playerJson = Json.encodeToString(player.toDto())
        val encodedPlayerJson = URLEncoder.encode(playerJson, "UTF-8")
        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/createWaitingRoom?waitingRoomId=$waitingRoomId&player=$encodedPlayerJson")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onSuccess()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                listeners.forEach { it(text) }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onFailure(t)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.cancel()
                this@SocketManager.webSocket = null
            }
        })
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closing normally")
        webSocket = null
    }

    // 메시지 수신 리스너 등록
    fun addListener(listener: (String) -> Unit) {
        listeners.add(listener)
    }

    fun removeListener(listener: (String) -> Unit) {
        listeners.remove(listener)
    }
}
