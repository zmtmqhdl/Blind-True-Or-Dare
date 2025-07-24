package com.example.domain.model

data class CreateRoom(
    val roomId: String
)

data class Room(
    val roomId: String,
    val hostId: String,
    val participantList: List<Player>,
    var roomStatus: RoomStatus,
    val writeTime: Long,
    val questionNumber: Int
)

data class CreateRoomRequest(
    val player: Player
)

data class Player(
    val playerId: String,
    val nickname: String,
)

data class Question(
    val playerId: String,
    val question: String,
    val oCount: Int,
    val xCount: Int
)

enum class RoomStatus {
    WAIT,
    WRITE,
    ANSWER
}