package com.example.presentation.waitingRoom

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.lifecycle.viewModelScope
import com.example.data.client.WebSocketClient
import com.example.domain.model.WaitingRoom
import com.example.core.core.ProjectViewModel
import com.example.data.client.WebSocketManager
import com.example.data.model.Message
import com.example.data.model.MessageType
import com.example.data.model.WaitingRoomDto
import com.example.data.toDomain
import com.example.domain.repository.WaitingRoomRepository
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class WaitingRoomViewModel @Inject constructor(
    waitingRoomRepository: WaitingRoomRepository
) : ProjectViewModel<WaitingRoomState, WaitingRoomEvent>(
    initialState = WaitingRoomState(),
    viewModelTag = "WaitingRoomViewModel"
) {

    init {
        viewModelScope.launch {
            WebSocketManager.message.collect { message ->
                when (message.type) {
                    MessageType.Update -> {
                        waitingRoomRepository.saveWaitingRoom(
                            waitingRoom = Json.decodeFromString<WaitingRoomDto>(message.data!!).toDomain()
                        )
                    }
                }
            }
        }
    }

    val waitingRoom: StateFlow<WaitingRoom?> = waitingRoomRepository.waitingRoom
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    val barCode: StateFlow<Bitmap?> = waitingRoomRepository.barCode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    private val _socketMessage = MutableStateFlow<String?>(null)
    val socketMessage: StateFlow<String?> = _socketMessage.asStateFlow()

    private var webSocketClient: WebSocketClient? = null

}

data class WaitingRoomState(
    val loading: Boolean = false,
)

sealed class WaitingRoomEvent {
    object Idle : WaitingRoomEvent()
    object ConnectWaitingRoomSuccess : WaitingRoomEvent()
    object ConnectWaitingRoomFailure : WaitingRoomEvent()
}
