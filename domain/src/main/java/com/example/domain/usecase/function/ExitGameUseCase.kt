package com.example.domain.usecase.function

import com.example.domain.usecase.SetPlayerUseCase
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.SetWaitingRoomUseCase
import javax.inject.Inject

class ExitGameUseCase @Inject constructor(
    private val setPlayerUseCase: SetPlayerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase,
    private val setWaitingRoomUseCase: SetWaitingRoomUseCase
) {
    operator fun invoke() {
        setWaitingRoomUseCase(data = null)
        setQrCodeUseCase(qrCode = null)
        setPlayerUseCase(player = null)
    }
}