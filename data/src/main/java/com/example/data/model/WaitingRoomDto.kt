package com.example.data.model

import com.example.domain.model.WaitingRoomStatus
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    val playerId: String,
    val nickname: String,
)

@Serializable
data class CreateWaitingRoomDto(
    val waitingRoomId: String
)


@Serializable
data class WaitingRoomDto (
    val waitingRoomId: String,
    val hostId: String,
    val participantList: List<PlayerDto>,
    val waitingRoomStatus: WaitingRoomStatus,
)

@Serializable
data class WaitingRoomDataDto (
    val waitingRoomId: String,
    val hostId: String,
    val participantList: List<PlayerDto>,
    val waitingRoomStatus: WaitingRoomStatus,
)

@Serializable
data class CreateWaitingRoomRequestDto(
    val player: PlayerDto
)