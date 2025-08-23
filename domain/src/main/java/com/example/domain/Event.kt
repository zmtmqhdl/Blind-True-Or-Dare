package com.example.domain

sealed class Event {
    class CreateRoomFailure(val error: Throwable) : Event()
    object WriteNextQuestion : Event()
    object AnswerNextQuestion : Event()
    object StartFailure: Event()
}

sealed class WebSocketStatus {
    class WebSocketConnectSuccess(val roomUrl: String) : WebSocketStatus()
    class WebSocketConnectFailure(val error: Throwable) : WebSocketStatus()
    object WebSocketDisconnect : WebSocketStatus()
}