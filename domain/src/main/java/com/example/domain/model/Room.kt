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
    val player: Player,
    val question: String,
    val oVoter: Set<Player>,
    val xVoter: Set<Player>,
    val noAnswer: Set<Player>
)

data class Answer(
    val questionId: Long,
    val player: Player,
    val answer: Boolean?
)

enum class RoomStatus {
    WAIT,
    WRITE,
    ANSWER,
    RESULT
}
