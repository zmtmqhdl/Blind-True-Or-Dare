package com.example.presentation.waitingRoom

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.model.WaitingRoom
import com.example.domain.repository.WaitingRoomRepository
import com.example.domain.usecase.ExitWaitingRoomUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.ui.graphics.asImageBitmap
import com.example.domain.model.MessageType
import com.example.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.flow.map

@HiltViewModel
class WaitingRoomViewModel @Inject constructor(
    private val waitingRoomRepository: WaitingRoomRepository,

    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val exitWaitingRoomUseCase: ExitWaitingRoomUseCase,
    private val handleWebSocketConnectUseCase: WebSocketHandlerUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ProjectViewModel(
    viewModelTag = "WaitingRoomViewModel"
) {
    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }
    }

    val waitingRoom: StateFlow<WaitingRoom?> = waitingRoomRepository.waitingRoom

    val qrCode: StateFlow<ImageBitmap?> = waitingRoomRepository.qrCode
        .map { it?.asImageBitmap() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    fun exitWaitingRoom() {
        exitWaitingRoomUseCase()
    }

    fun handleWebSocketConnect(
        onDisconnect: () -> Unit
    ) {
        viewModelScope.launch {
            handleWebSocketConnectUseCase(
                onDisconnect = {
                    waitingRoomRepository.setQrCode(qrCode = null)
                    onDisconnect()
                }
            )
        }
    }

    fun sendStartMessage() {
        sendMessageUseCase(
            messageType = MessageType.SEND_START
        )
    }
}