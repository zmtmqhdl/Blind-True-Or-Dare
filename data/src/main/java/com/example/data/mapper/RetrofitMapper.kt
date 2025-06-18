package com.example.data.mapper

import com.example.data.model.CreateWaitingRoomResponse
import com.example.data.model.PlayerDto
import com.example.data.model.WaitingRoomDataDto
import com.example.data.model.WaitingRoomDto
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.User
import com.example.domain.model.WaitingRoom
import com.example.domain.model.WaitingRoomStatus

fun PlayerDto.toDomain(): User =
    User(
        playerId = this@toDomain.playerId,
        nickname = nickname
    )


fun User.toDto(): PlayerDto =
    PlayerDto(
        playerId = playerId,
        nickname = nickname
    )

fun CreateWaitingRoomResponse.toDomain() : CreateWaitingRoom =
    CreateWaitingRoom(
        waitingRoomId = waitingRoomId
    )



fun WaitingRoomDataDto.toDomain(): WaitingRoom =
    WaitingRoom(
        roomId = roomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )

fun WaitingRoom.toDto(): WaitingRoomDataDto =
    WaitingRoomDataDto(
        roomId = roomId,
        hostId = hostId,
        participantList = participantList.map { it.toDto()},
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )





fun WaitingRoomDto.toWaitingRoom(): WaitingRoom =
    WaitingRoom(
        roomId = roomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )



