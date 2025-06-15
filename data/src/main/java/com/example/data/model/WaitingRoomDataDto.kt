package com.example.data.model

import com.example.domain.model.WaitingRoomStatus


data class UserDto(
    val userId: String,
    val nickname: String,
)

data class CreateWaitingRoomRequest(
    val user: UserDto
)

data class CreateWaitingRoomResponse(
    val waitingRoomId: String
)


data class WaitingRoomDto (
    val roomId: String,
    val hostId: String,
    val participantList: List<UserDto>,
    val waitingRoomStatus: WaitingRoomStatus,
)


data class WaitingRoomDataDto (
    val roomId: String,
    val hostId: String,
    val participantList: List<UserDto>,
    val waitingRoomStatus: WaitingRoomStatus,
)