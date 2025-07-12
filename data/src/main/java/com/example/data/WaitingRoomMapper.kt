package com.example.data

import com.example.data.model.CreateWaitingRoomDto
import com.example.data.model.CreateWaitingRoomRequestDto
import com.example.data.model.PlayerDto
import com.example.data.model.WaitingRoomDataDto
import com.example.data.model.WaitingRoomDto
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.CreateWaitingRoomRequest
import com.example.domain.model.Player
import com.example.domain.model.WaitingRoom
import com.example.domain.model.WaitingRoomStatus

fun Player.toDto(): PlayerDto =
    PlayerDto(
        playerId = playerId,
        nickname = nickname
    )

fun CreateWaitingRoomRequest.toDto() : CreateWaitingRoomRequestDto =
    CreateWaitingRoomRequestDto(
        player = player.toDto()
    )

fun PlayerDto.toDomain(): Player =
    Player(
        playerId = this@toDomain.playerId,
        nickname = nickname
    )




fun CreateWaitingRoomDto.toDomain() : CreateWaitingRoom =
    CreateWaitingRoom(
        waitingRoomId = waitingRoomId
    )



fun WaitingRoomDataDto.toDomain(): WaitingRoom =
    WaitingRoom(
        waitingRoomId = waitingRoomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )

fun WaitingRoom.toDto(): WaitingRoomDataDto =
    WaitingRoomDataDto(
        waitingRoomId = waitingRoomId,
        hostId = hostId,
        participantList = participantList.map { it.toDto()},
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )





fun WaitingRoomDto.toWaitingRoom(): WaitingRoom =
    WaitingRoom(
        waitingRoomId = waitingRoomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )