package com.example.presentation.content.waitingRoom

import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.model.MessageType
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.DisconnectWebSocketUseCase
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SendMessageUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.ExitGameFunction
import com.example.domain.usecase.function.GameStartFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitingRoomViewModel @Inject constructor(
    roomRepository: RoomRepository,
    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val disconnectWebSocketUseCase: DisconnectWebSocketUseCase,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val exitGameFunction: ExitGameFunction,
    private val gameStartFunction: GameStartFunction,
    private val eventHandlerUseCase: EventHandlerUseCase
) : ProjectViewModel(
    viewModelTag = "WaitingRoomViewModel"
) {
    val room = roomRepository.room
    val player = roomRepository.player

    private val _startFailureDialog = MutableStateFlow(false)
    val startFailureDialog: StateFlow<Boolean> = _startFailureDialog.asStateFlow()

    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }

        viewModelScope.launch {
            eventHandlerUseCase(
                startFailure = { _startFailureDialog.value = true }
            )
        }
    }


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
                    exitGameFunction()
                    onDisconnect()
                }
            )
        }
    }

    fun sendStartMessage() {
        viewModelScope.launch {
            gameStartFunction()
        }
    }

    fun dismissStartFailureDialog() {
        _startFailureDialog.value = false
    }
}