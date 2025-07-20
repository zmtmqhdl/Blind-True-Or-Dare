package com.example.domain

sealed class Event {
    class CreateWaitingRoomFailure(val error: Throwable) : Event()
}

sealed class WebSocketStatus {
    class WebSocketConnectSuccess(val waitingRoomUrl: String): WebSocketStatus()
    class  WebSocketConnectFailure(val error: Throwable): WebSocketStatus()
    object WebSocketDisconnect: WebSocketStatus()
}