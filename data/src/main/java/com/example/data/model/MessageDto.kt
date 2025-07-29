package com.example.data.model

import com.example.domain.model.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val type: MessageType,
    val playerId: String? = null,
    val data: String? = null,
    val timestamp: Long
)