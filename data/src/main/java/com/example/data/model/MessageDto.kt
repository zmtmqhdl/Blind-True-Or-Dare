package com.example.data.model

import com.example.domain.model.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val type: MessageType,
    val senderId: String? = null,
    val data: String? = null,
    val timestamp: Long
)

@Serializable
data class GameStartDto (
    val waitingRoom: WaitingRoomDto,
    val questionRoomSetting: QuestionSettingDto
)