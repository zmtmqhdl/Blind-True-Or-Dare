package com.example.domain.model

data class CreateWaitingRoom(
    val waitingRoomId: String
)

data class WaitingRoom(
    val waitingRoomId: String,
    val hostId: String,
    val participantList: List<User> = emptyList<User>(),
    val waitingRoomStatus: WaitingRoomStatus
)

data class User(
    val playerId: String = "",
    val nickname: String = "",
)

enum class WaitingRoomStatus {
    Waiting,
    Playing
}