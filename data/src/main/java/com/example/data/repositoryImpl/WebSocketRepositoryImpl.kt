package com.example.data.repositoryImpl

import android.util.Log
import com.example.data.model.AnswerDto
import com.example.data.model.MessageDto
import com.example.data.model.QuestionDto
import com.example.data.toDomain
import com.example.data.toDto
import com.example.domain.WebSocketStatus
import com.example.domain.model.Answer
import com.example.domain.model.Message
import com.example.domain.model.MessageType
import com.example.domain.model.Player
import com.example.domain.model.Question
import com.example.domain.repository.WebSocketRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class WebSocketRepositoryImpl @Inject constructor(
) : WebSocketRepository {
    private val _webSocketConnect = MutableSharedFlow<WebSocketStatus>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    override val webSocketConnect: SharedFlow<WebSocketStatus> = _webSocketConnect.asSharedFlow()

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _message = MutableSharedFlow<Message?>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    override val message: SharedFlow<Message?> = _message.asSharedFlow()

    override fun connect(
        roomId: String,
        player: Player
    ) {
        if (webSocket != null) return

        val playerJson = Json.encodeToString(player.toDto())
        val encodedPlayerJson = URLEncoder.encode(playerJson, "UTF-8")
        val roomUrl = "ws://10.0.2.2:8080/room?roomId=$roomId"
        val request = Request.Builder()
            .url("$roomUrl&player=$encodedPlayerJson")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                scope.launch {
                    _webSocketConnect.emit(value = WebSocketStatus.WebSocketConnectSuccess(roomUrl = roomUrl))
                }
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                scope.launch {
                    _message.emit(value = Json.decodeFromString<MessageDto>(text).toDomain())
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                scope.launch {
                    _webSocketConnect.emit(value = WebSocketStatus.WebSocketConnectFailure(error = t))
                }

            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                this@WebSocketRepositoryImpl.webSocket = null
                scope.launch {
                    _webSocketConnect.emit(value = WebSocketStatus.WebSocketDisconnect)
                }
            }
        })
    }

    override fun send(
        messageType: MessageType,
        playerId: String,
        data: Any?,
        timestamp: Long
    ) {
        val message = when (messageType) {
            MessageType.SEND_START -> {
                Message(
                    type = MessageType.SEND_START,
                    playerId = playerId,
                    timestamp = System.currentTimeMillis()
                )
            }

            MessageType.SEND_WRITE_END -> {
                val questionList = data as List<Question>
                Message(
                    type = MessageType.SEND_WRITE_END,
                    playerId = playerId,
                    data = Json.encodeToString<List<QuestionDto>>(value = questionList.map { it.toDto() }),
                    timestamp = System.currentTimeMillis()
                )
            }

            MessageType.SEND_ANSWER_END -> {
                val answerList = data as List<Answer>
                Message(
                    type = MessageType.SEND_ANSWER_END,
                    playerId = playerId,
                    data = Json.encodeToString<List<AnswerDto>>(value = answerList.map { it.toDto() }),
                    timestamp = System.currentTimeMillis()
                )
            }

            else -> null
        }
        message?.let {
            webSocket?.send(text = Json.encodeToString(value = message.toDto()))

        }
    }

    override fun close() {
        webSocket?.close(1000, "Closing normally")
    }
}