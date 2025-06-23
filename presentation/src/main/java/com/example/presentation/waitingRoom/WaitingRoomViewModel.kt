package com.example.presentation.waitingRoom

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.lifecycle.viewModelScope
import com.example.data.client.WebSocketClient
import com.example.domain.model.WaitingRoom
import com.example.presentation.core.ProjectViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WaitingRoomViewModel @Inject constructor(
) : ProjectViewModel<WaitingRoomState, WaitingRoomEvent>(
    initialState = WaitingRoomState(),
    viewModelTag = "WaitingRoomViewModel"
) {
    val loading: StateFlow<Boolean> = state
        .map { it.loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    private val _waitingRoom = MutableStateFlow<WaitingRoom?>(null)
    val waitingRoom: StateFlow<WaitingRoom?> = _waitingRoom.asStateFlow()

    private val _barCode = MutableStateFlow<Bitmap?>(null)
    val barCode: StateFlow<Bitmap?> = _barCode.asStateFlow()

    private val _socketMessage = MutableStateFlow<String?>(null)
    val socketMessage: StateFlow<String?> = _socketMessage.asStateFlow()

    private var webSocketClient: WebSocketClient? = null

    fun connectWaitingRoom(
        waitingRoomId: String,
        playerId: String
    ) {
        setState { copy(loading = true) }
        logD(
            """
            [fun connectWaitingRoom parameter]
                waitingRoomId = $waitingRoomId,
                playerId = $playerId
        """.trimIndent()
        )
        webSocketClient = WebSocketClient(
            waitingRoomId = waitingRoomId,
            playerId = playerId,
            onMessage = { message ->
                _socketMessage.value = message
            },
            onOpen = {
                setState { copy(loading = false) }
                setEvent(
                    event = WaitingRoomEvent.ConnectWaitingRoomSuccess
                )
                logD("[fun connectWaitingRoom success]")
            },
            onFailure = { throwable ->
                setState { copy(loading = false) }
                setEvent(
                    event = WaitingRoomEvent.ConnectWaitingRoomFailure
                )
                logD("[fun connectWaitingRoom failure]")

            }
        ).also {
            it.connect()
        }
    }

    fun createBarCode(waitingRoomId: String): Bitmap {
        val width = 600
        val height = 300
        val bitMatrix = MultiFormatWriter().encode(
            waitingRoomId,
            BarcodeFormat.CODE_128,
            width,
            height,
            null
        )
        val bmp = createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp[x, y] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }
        return bmp
    }
}

data class WaitingRoomState(
    val loading: Boolean = false,
)

sealed class WaitingRoomEvent {
    object Idle : WaitingRoomEvent()
    object ConnectWaitingRoomSuccess : WaitingRoomEvent()
    object ConnectWaitingRoomFailure : WaitingRoomEvent()
}
