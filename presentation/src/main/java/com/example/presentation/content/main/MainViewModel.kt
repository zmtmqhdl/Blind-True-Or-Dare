package com.example.presentation.content.main

import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.ConnectWebSocketUseCase
import com.example.domain.usecase.CreateRoomUseCase
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.HideLoadingUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SetPlayerUseCase
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.ShowLoadingUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.CreateRoomFunction
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
class MainViewModel @Inject constructor(
    private val roomRepository: RoomRepository,

    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val eventHandlerUseCase: EventHandlerUseCase,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase,
    private val createRoomFunction: CreateRoomFunction,
    private val joinRoomFunction: JoinRoomFunction
) : ProjectViewModel(
    viewModelTag = "MainViewModel"
) {
    private val _createRoomFailureDialog = MutableStateFlow(false)
    val createRoomFailureDialog: StateFlow<Boolean> = _createRoomFailureDialog.asStateFlow()

    private val _joinRoomFailureDialog = MutableStateFlow(false)
    val joinRoomFailureDialog: StateFlow<Boolean> = _joinRoomFailureDialog.asStateFlow()

    private var job: Job? = null

    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }

        viewModelScope.launch {
            eventHandlerUseCase(
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

    fun handleWebSocketConnect(
        onConnect: () -> Unit
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

    fun dismissCreateRoomFailureDialog() {
        _createRoomFailureDialog.value = false
    }

    fun dismissJoinRoomFailureDialog() {
        _joinRoomFailureDialog.value = false
    }
}