package com.example.data.model

import com.example.domain.model.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val type: MessageType,
    val player: PlayerDto? = null,
    val data: String? = null,
    val timestamp: Long
)