package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionRoomSettingDto (
    val time: Long,
    val number: Int
)

@Serializable
data class GameStartDto (
    val waitingRoom: WaitingRoomDto,
    val questionRoomSetting: QuestionRoomSettingDto
)