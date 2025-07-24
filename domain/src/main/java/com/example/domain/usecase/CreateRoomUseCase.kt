package com.example.domain.usecase

import com.example.domain.common.ApiResponse
import com.example.domain.model.CreateRoom
import com.example.domain.model.Player
import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
) {
    suspend operator fun invoke(
        player: Player,
    ): ApiResponse<CreateRoom> {
        return roomRepository.createRoom(
            player = player
        )
    }
}