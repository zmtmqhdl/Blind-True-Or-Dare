package com.example.data.model

import com.example.domain.model.RoomStatus
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    val playerId: String,
    val nickname: String,
)

@Serializable
data class CreateRoomDto(
    val roomId: String
)


@Serializable
data class RoomDto (
    val roomId: String,
    val host: PlayerDto,
    val participantList: Set<PlayerDto>,
    val roomStatus: RoomStatus,
    val writeTime: Long,
    val questionNumber: Long,
    val questionList: List<QuestionDto>
)

@Serializable
data class CreateRoomRequestDto(
    val player: PlayerDto
)

@Serializable
data class QuestionDto(
    val questionId: Long,
    val playerId: String,
    val question: String,
    val oVoters: Set<String>,
    val xVoters: Set<String>
)

@Serializable
data class AnswerDto(
    val questionId: Long,
    val playerId: String,
    val answer: Boolean?
)