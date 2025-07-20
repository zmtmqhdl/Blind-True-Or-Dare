package com.example.domain.usecase

import android.graphics.Bitmap
import com.example.domain.repository.WaitingRoomRepository
import javax.inject.Inject

class SetQrCodeUseCase @Inject constructor(
    private val waitingRoomRepository: WaitingRoomRepository
) {
    operator fun invoke(
        qrCode: Bitmap?
    ) {
        waitingRoomRepository.setQrCode(
            qrCode = qrCode
        )
    }
}