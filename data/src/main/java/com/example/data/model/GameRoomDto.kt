package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionSettingDto (
    val time: Long,
    val number: Int
)

@Serializable
data class QuestionDto(
    val playerId: String,
    val question: String,
    val oCount: Int,
    val xCount: Int
)