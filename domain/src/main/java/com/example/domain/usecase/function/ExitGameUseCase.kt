package com.example.domain.usecase.function

import com.example.domain.usecase.SetPlayerUseCase
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.SetRoomUseCase
import javax.inject.Inject

class ExitGameUseCase @Inject constructor(
    private val setPlayerUseCase: SetPlayerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase,
    private val setRoomUseCase: SetRoomUseCase
) {
    operator fun invoke() {
        setRoomUseCase(data = null)
        setQrCodeUseCase(qrCode = null)
        setPlayerUseCase(player = null)
    }
}