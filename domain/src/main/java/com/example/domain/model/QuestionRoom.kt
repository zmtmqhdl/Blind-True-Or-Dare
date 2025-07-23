package com.example.domain.model

data class QuestionRoomSetting (
    val time: Long,
    val number: Int
)

data class GameStart (
    val waitingRoom: WaitingRoom,
    val questionRoomSetting: QuestionRoomSetting
)