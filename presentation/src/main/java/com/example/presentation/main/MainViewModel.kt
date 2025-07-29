package com.example.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.Event
import com.example.domain.common.onFailure
import com.example.domain.common.onSuccess
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.CreateRoomUseCase
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.HideLoadingUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SetPlayerUseCase
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.ShowLoadingUseCase
import com.example.domain.usecase.ConnectWebSocketUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.generatePlayerUseCase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomRepository: RoomRepository,

    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val hideLoadingUseCase: HideLoadingUseCase,
    private val showLoadingUseCase: ShowLoadingUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    private val emitEventUseCase: EmitEventUseCase,
    private val eventHandlerUseCase: EventHandlerUseCase,
    private val connectWebSocketUseCase: ConnectWebSocketUseCase,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase,
    private val setPlayerUseCase: SetPlayerUseCase
) : ProjectViewModel(
    viewModelTag = "MainViewModel"
) {
    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }
    }

    fun createRoom(
        nickname: String
    ) {
        showLoadingUseCase()
        logD(
            """
            [fun createRoom start]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            val player = generatePlayerUseCase(nickname = nickname)
            createRoomUseCase(
                player = player
            ).onSuccess {
                setPlayerUseCase(player = player)
                connectWebSocketUseCase(
                    roomId = it.roomId,
                    player = player
                )
                hideLoadingUseCase()
            }.onFailure {
                emitEventUseCase(event = Event.CreateRoomFailure(error = it))
            }
        }
    }

    fun joinRoom(
        nickname: String,
        waitingRoomId: String
    ) {
        showLoadingUseCase()
        logD(
            """
            [fun joinRoom start]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            val player = generatePlayerUseCase(nickname = nickname)
            setPlayerUseCase(player = player)
            connectWebSocketUseCase(
                roomId = waitingRoomId,
                player = player
            )
            hideLoadingUseCase()
        }
    }

    fun eventHandler(
        createWaitingRoomFailure: () -> Unit,
    ) {
        viewModelScope.launch {
            eventHandlerUseCase.invoke(
                createRoomFailure = createWaitingRoomFailure,
            )
        }
    }

    fun handleWebSocketConnect(
        onConnect: () -> Unit,
        onConnectFailure: (Throwable) -> Unit,
    ) {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                onConnect = {
                    setQrCodeUseCase(
                        qrCode = BarcodeEncoder().createBitmap(
                            MultiFormatWriter().encode(
                                it,
                                BarcodeFormat.QR_CODE,
                                512,
                                512
                            )
                        )
                    )
                    onConnect()
                },
                onConnectFailure = { onConnectFailure(it) },
            )
        }
    }
}