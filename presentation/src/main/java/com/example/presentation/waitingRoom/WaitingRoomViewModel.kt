package com.example.presentation.waitingRoom

import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.model.MessageType
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.DisconnectWebSocketUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SendMessageUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.ExitGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitingRoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository,

    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val disconnectWebSocketUseCase: DisconnectWebSocketUseCase,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val exitGameUseCase: ExitGameUseCase
) : ProjectViewModel(
    viewModelTag = "WaitingRoomViewModel"
) {
    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }
    }

    val room = roomRepository.room

    val qrCode = roomRepository.qrCode
        .map { it?.asImageBitmap() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    fun exitRoom() {
        disconnectWebSocketUseCase()
    }

    fun handleWebSocketConnect(
        onDisconnect: () -> Unit
    ) {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                onDisconnect = {
                    exitGameUseCase()
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