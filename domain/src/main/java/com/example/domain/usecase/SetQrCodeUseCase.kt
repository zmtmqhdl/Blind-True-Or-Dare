package com.example.domain.usecase

import android.graphics.Bitmap
import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class SetQrCodeUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        qrCode: Bitmap?
    ) {
        roomRepository.setQrCode(
            qrCode = qrCode
        )
    }
}