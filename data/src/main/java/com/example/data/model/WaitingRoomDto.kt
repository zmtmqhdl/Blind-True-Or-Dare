package com.example.data.model

import com.example.domain.model.WaitingRoomStatus

data class PlayerDto(
    val playerId: String,
    val nickname: String,
)

data class CreateWaitingRoomDto(
    val waitingRoomId: String
)


data class WaitingRoomDto (
    val waitingRoomId: String,
    val hostId: String,
    val participantList: List<PlayerDto>,
    val waitingRoomStatus: WaitingRoomStatus,
)


data class WaitingRoomDataDto (
    val waitingRoomId: String,
    val hostId: String,
    val participantList: List<PlayerDto>,
    val waitingRoomStatus: WaitingRoomStatus,
)

data class CreateWaitingRoomRequestDto(
    val player: PlayerDto
)