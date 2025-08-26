package com.example.domain.model

data class CreateRoom(
    val roomId: String
)

data class Room(
    val roomId: String,
    val host: Player,
    val participantList: Set<Player>,
    var roomStatus: RoomStatus,
    val writeTime: Long,
    val questionNumber: Long,
    val questionList: List<Question>
)

data class CreateRoomRequest(
    val player: Player
)

data class Player(
    val playerId: String,
    val nickname: String,
)

data class Question(
    val questionId: Long,
    val playerId: String,
    val question: String,
    val oVoters: Set<String>,
    val xVoters: Set<String>
)

data class Answer(
    val questionId: Long,
    val playerId: String,
    val answer: Boolean
)

enum class RoomStatus {
    WAIT,
    READY,
    WRITE,
    ANSWER,
    END
}