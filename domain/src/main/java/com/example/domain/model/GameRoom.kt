package com.example.domain.model

data class QuestionSetting (
    val time: Long,
    val number: Int
)

data class Question(
    val playerId: String,
    val question: String,
    val oCount: Int,
    val xCount: Int
)