package com.example.domain.model

import android.graphics.Bitmap

data class WaitingRoomData(
    val roomId: String,
    val hostId: String,
    val participantList: List<User> = emptyList<User>(),
    val status: Status = Status.WAITING,
)

data class User(
    val id: String = "",
    val nickname: String = "",
)

enum class Status {
    WAITING,
    PLAYING
}