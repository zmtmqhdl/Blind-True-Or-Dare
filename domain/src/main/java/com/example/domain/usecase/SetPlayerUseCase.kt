package com.example.domain.usecase

import com.example.domain.model.Player
import com.example.domain.repository.WaitingRoomRepository
import javax.inject.Inject

class SetPlayerUseCase @Inject
constructor(
    private val waitingRoomRepository: WaitingRoomRepository
) {
    operator fun invoke(
        player: Player?
    ) {
        waitingRoomRepository.setPlayer(
            player = player
        )
    }
}