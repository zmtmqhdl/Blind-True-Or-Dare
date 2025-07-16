package com.example.domain.usecase

import com.example.domain.model.WaitingRoom
import com.example.domain.repository.WaitingRoomRepository
import javax.inject.Inject
import kotlinx.serialization.json.Json

class SaveWaitingRoom @Inject constructor(
    private val waitingRoomRepository: WaitingRoomRepository
) {
    operator fun invoke(
        data: String
    ) {
        waitingRoomRepository.saveWaitingRoom(
            waitingRoom = Json.decodeFromString<WaitingRoom>(data)
        )
    }
}