package com.example.domain.model

data class Message (
    val type: MessageType,
    val senderId: String? = null,
    val data: String? = null,
    val timestamp: Long
)

data class GameStart (
    val waitingRoom: WaitingRoom,
    val questionSetting: QuestionSetting
)


enum class MessageType {
    UPDATE, SEND_START, START
}