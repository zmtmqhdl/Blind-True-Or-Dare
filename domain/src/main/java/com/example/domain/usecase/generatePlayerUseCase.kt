package com.example.domain.usecase

import com.example.domain.model.Player
import java.math.BigInteger
import java.util.UUID

fun generatePlayerUseCase(
    nickname: String
): Player {
    return Player(
        playerId = UUID.randomUUID()
            .toString()
            .replace("-", "")
            .let { BigInteger(it, 16) }
            .toString(62),
        nickname = nickname
    )
}