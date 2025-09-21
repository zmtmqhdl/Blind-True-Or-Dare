package com.example.presentation.content.scanQrCode

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanQrCodeViewModel @Inject constructor(
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase
): ProjectViewModel(
    viewModelTag = "ScanQrCodeViewModel"
) {
    private var job: Job? = null


    fun handleWebSocketConnect(
        onConnect: () -> Unit,
    ) {
        if (job?.isActive == true) return
        job = viewModelScope.launch {
            webSocketHandlerUseCase(
                onConnect = {
                    setQrCodeUseCase(
                        qrCode = BarcodeEncoder().createBitmap(
                            MultiFormatWriter().encode(
                                it,
                                BarcodeFormat.QR_CODE,
                                1024,
                                1024
                            )
                        )
                    )
                    onConnect()
                },
                onConnectFailure = { logD("$it") },
            )
        }
    }
}