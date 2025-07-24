package com.example.domain.model

data class Message (
    val type: MessageType,
    val senderId: String? = null,
    val data: String? = null,
    val timestamp: Long
)


enum class MessageType {
    UPDATE, SEND_START,
}