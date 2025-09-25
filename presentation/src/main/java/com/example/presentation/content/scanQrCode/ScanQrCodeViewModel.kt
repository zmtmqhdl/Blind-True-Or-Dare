package com.example.presentation.content.scanQrCode

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.JoinRoomFunction
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanQrCodeViewModel @Inject constructor(
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase,
    private val joinRoomFunction: JoinRoomFunction
): ProjectViewModel(
    viewModelTag = "ScanQrCodeViewModel"
) {
    private val _joinRoomDialog = MutableStateFlow(false)
    val joinRoomDialog: StateFlow<Boolean> = _joinRoomDialog.asStateFlow()

    private var job: Job? = null

    fun scanQr() {
        // qr 촬영 이후의 로직 필요
        _joinRoomDialog.value = true
    }

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
                viewModelScope = this,
            )
        }
    }

    fun joinRoom(
        nickname: String,
        roomId: String
    ) {
        viewModelScope.launch {
            joinRoomFunction(
                nickname = nickname,
                roomId = roomId
            )
        }
    }
}