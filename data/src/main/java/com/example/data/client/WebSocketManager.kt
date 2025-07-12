package com.example.data.client

import android.util.Log
import com.example.data.model.Message
import com.example.data.toDto
import com.example.domain.model.Player
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.URLEncoder

object WebSocketManager {
    val tag = "WebSocketManger"

    private val client: OkHttpClient
        get() = OkHttpClient()
    private var webSocket: WebSocket? = null

    private val listeners = mutableListOf<(Message) -> Unit>()

    fun connect(
        waitingRoomId: String,
        player: Player,
        onSuccess: () -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ) {
        if (webSocket != null) return

        val playerJson = Json.encodeToString(player.toDto())
        val encodedPlayerJson = URLEncoder.encode(playerJson, "UTF-8")
        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/waitingRoom?waitingRoomId=$waitingRoomId&player=$encodedPlayerJson")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onSuccess()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(tag, "$text")
                try {
                    val message = Json.decodeFromString<Message>(text)
                    Log.d(tag, "$message")
                    listeners.forEach { it(message) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onFailure(t)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.cancel()
                this@WebSocketManager.webSocket = null
            }
        }
        )
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closing normally")
        webSocket = null
    }

    fun addListener(listener: (Message) -> Unit) {
        listeners.add(listener)
    }

    fun removeListener(listener: (Message) -> Unit) {
        listeners.remove(listener)
    }
}
