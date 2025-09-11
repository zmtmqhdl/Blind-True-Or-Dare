package com.example.presentation.content

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.repository.UiFlowRepository
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.ExitGameFunction
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    uiFlowRepository: UiFlowRepository,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase,
    private val exitGameFunction: ExitGameFunction,

): ProjectViewModel(
    viewModelTag = "ContentViewModel"
) {
    val loading = uiFlowRepository.loading

    private var job: Job? = null

    fun handleWebSocketConnect(
        onConnect: () -> Unit,
        onDisconnect: () -> Unit
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
                onDisconnect = {
                    exitGameFunction()
                    onDisconnect()
                },
                onConnectFailure = { logD("$it") },
            )
        }
    }
}