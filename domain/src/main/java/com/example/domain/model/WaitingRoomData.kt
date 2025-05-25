package com.example.domain.model

data class WaitingRoomData(
    val status: Status = Status.WAITING,
    val participantList: List<User> = emptyList<User>()
)

data class User(
    val id: String = "",
    val nickname: String = "",
)

data class CreateWaitingRoomData(
    val roomId: String = "",
    val userId: String = ""
)

enum class Status {
    WAITING,
    PLAYING
}