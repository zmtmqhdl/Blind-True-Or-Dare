package com.example.domain.usecase

import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class SetRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        data: String?
    ) {
        roomRepository.setRoom(
            data = data
        )
    }
}