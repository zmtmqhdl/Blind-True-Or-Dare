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
    val hostId: String,
    val participantList: List<PlayerDto>,
    val roomStatus: RoomStatus,
    val writeTime: Long,
    val questionNumber: Int
)

@Serializable
data class CreateRoomRequestDto(
    val player: PlayerDto
)

@Serializable
data class QuestionDto(
    val playerId: String,
    val question: String,
    val oCount: Int,
    val xCount: Int
)