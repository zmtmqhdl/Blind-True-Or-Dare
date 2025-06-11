package com.example.data.mapper

import com.example.data.model.UserDto
import com.example.data.model.WaitingRoomDataDto
import com.example.domain.model.User
import com.example.domain.model.WaitingRoomData

fun WaitingRoomDataDto.toDomain(): WaitingRoomData =
    WaitingRoomData(
        roomId = roomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        status = status
    )

fun WaitingRoomData.toDto(): WaitingRoomDataDto =
    WaitingRoomDataDto(
        roomId = roomId,
        hostId = hostId,
        participantList = participantList.map { it.toDto()},
        status = status
    )

fun UserDto.toDomain(): User =
    User(
        id = id,
        nickname = nickname
    )


fun User.toDto(): UserDto =
    UserDto(
        id = id,
        nickname = nickname
    )
