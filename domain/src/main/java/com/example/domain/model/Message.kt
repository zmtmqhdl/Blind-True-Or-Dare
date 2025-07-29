package com.example.domain.model

data class Message (
    val type: MessageType,
    val playerId: String? = null,
    val data: String? = null,
    val timestamp: Long
)


enum class MessageType {
    UPDATE, SEND_START, SEND_WRITE_END, SEND_ANSWER_END
}