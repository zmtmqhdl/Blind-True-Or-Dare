package com.example.domain.model

data class Message (
    val type: MessageType,
    val data: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

enum class MessageType {
    Update,
}