package com.example.domain.model

data class CreateWaitingRoom(
    val waitingRoomId: String
)

data class WaitingRoom(
    val waitingRoomId: String,
    val hostId: String,
    val participantList: List<Player> = emptyList(),
    var waitingRoomStatus: WaitingRoomStatus
)

data class CreateWaitingRoomRequest(
    val player: Player
)

data class Player(
    val playerId: String = "",
    val nickname: String = "",
)

enum class WaitingRoomStatus {
    Waiting,
    Playing
}