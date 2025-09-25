package com.example.presentation.content.main

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.CreateRoomFunction
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
class MainViewModel @Inject constructor(
    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val eventHandlerUseCase: EventHandlerUseCase,
    private val createRoomFunction: CreateRoomFunction,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase
) : ProjectViewModel(
    viewModelTag = "MainViewModel"
) {
    private val _createRoomFailureDialog = MutableStateFlow(false)
    val createRoomFailureDialog: StateFlow<Boolean> = _createRoomFailureDialog.asStateFlow()

    private val _joinRoomFailureDialog = MutableStateFlow(false)
    val joinRoomFailureDialog: StateFlow<Boolean> = _joinRoomFailureDialog.asStateFlow()

    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }

        viewModelScope.launch {
            eventHandlerUseCase(
                viewModelScope = this,
                createRoomFailure = { _createRoomFailureDialog.value = true },
            )
        }
    }

    fun createRoom(
        nickname: String
    ) {
        viewModelScope.launch {
            createRoomFunction(
                nickname = nickname
            )
        }
    }

    fun handleWebSocketConnect(
        onConnect: () -> Unit,
    ) {
        viewModelScope.launch {
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
                viewModelScope = this
            )
        }
    }



    fun dismissCreateRoomFailureDialog() {
        _createRoomFailureDialog.value = false
    }

    fun dismissJoinRoomFailureDialog() {
        _joinRoomFailureDialog.value = false
    }
}