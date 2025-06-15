package com.example.data.mapper

import com.example.data.model.CreateWaitingRoomResponse
import com.example.data.model.UserDto
import com.example.data.model.WaitingRoomDataDto
import com.example.data.model.WaitingRoomDto
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.User
import com.example.domain.model.WaitingRoom
import com.example.domain.model.WaitingRoomStatus

fun UserDto.toDomain(): User =
    User(
        userId = userId,
        nickname = nickname
    )


fun User.toDto(): UserDto =
    UserDto(
        userId = userId,
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



