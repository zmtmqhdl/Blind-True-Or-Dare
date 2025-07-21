package com.example.domain.usecase

import com.example.domain.common.ApiResponse
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.Player
import com.example.domain.repository.WaitingRoomRepository
import javax.inject.Inject

class CreateWaitingRoomUseCase @Inject constructor(
    private val waitingRoomRepository: WaitingRoomRepository,
) {
    suspend operator fun invoke(
        player: Player,
    ): ApiResponse<CreateWaitingRoom> {
        return waitingRoomRepository.createWaitingRoom(
            player = player
        )
    }
}