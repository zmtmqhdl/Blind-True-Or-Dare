package com.example.domain.usecase

import com.example.domain.model.Player
import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class SetPlayerUseCase @Inject
constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        player: Player?
    ) {
        roomRepository.setPlayer(
            player = player
        )
    }
}