package com.example.domain.usecase

import com.example.domain.repository.WaitingRoomRepository
import javax.inject.Inject

class SaveWaitingRoomUseCase @Inject constructor(
    private val waitingRoomRepository: WaitingRoomRepository
) {
    operator fun invoke(
        data: String
    ) {
        waitingRoomRepository.setWaitingRoom(
            data = data
        )
    }
}