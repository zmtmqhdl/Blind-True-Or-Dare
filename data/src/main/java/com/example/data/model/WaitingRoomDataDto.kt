package com.example.data.model

import com.example.domain.model.Status
import com.example.domain.model.User

data class WaitingRoomDataDto (
    val roomId: String,
    val hostId: String,
    val participantList: List<UserDto> = emptyList<UserDto>(),
    val status: Status = Status.WAITING,
)

data class UserDto(
    val id: String = "",
    val nickname: String = "",
)