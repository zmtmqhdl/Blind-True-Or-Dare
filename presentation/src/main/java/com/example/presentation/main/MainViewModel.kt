package com.example.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.Event
import com.example.domain.common.onFailure
import com.example.domain.common.onSuccess
import com.example.domain.repository.WaitingRoomRepository
import com.example.domain.usecase.CreateWaitingRoomUseCase
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.HandleEventUseCase
import com.example.domain.usecase.HideLoadingUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SetQrCodeUseCase
import com.example.domain.usecase.ShowLoadingUseCase
import com.example.domain.usecase.WebSocketConnectUseCase
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
    private val waitingRoomRepository: WaitingRoomRepository,

    private val messageHandlerUseCase: MessageHandlerUseCase,
    private val hideLoadingUseCase: HideLoadingUseCase,
    private val showLoadingUseCase: ShowLoadingUseCase,
    private val createWaitingRoomUseCase: CreateWaitingRoomUseCase,
    private val emitEventUseCase: EmitEventUseCase,
    private val handleEventUseCase: HandleEventUseCase,
    private val webSocketConnectUseCase: WebSocketConnectUseCase,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val setQrCodeUseCase: SetQrCodeUseCase
) : ProjectViewModel(
    viewModelTag = "MainViewModel"
) {
    init {
        viewModelScope.launch {
            messageHandlerUseCase()
        }
    }

    fun createWaitingRoom(
        nickname: String
    ) {
        showLoadingUseCase()
        logD(
            """
            [fun createWaitingRoom start]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            val player = generatePlayerUseCase(nickname = nickname)
            createWaitingRoomUseCase(
                player = player
            ).onSuccess {
                webSocketConnectUseCase(
                    waitingRoomId = it.waitingRoomId,
                    player = player
                )
                hideLoadingUseCase()
            }.onFailure {
                emitEventUseCase(event = Event.CreateWaitingRoomFailure(error = it))
            }
        }
    }

    fun joinWaitingRoom(
        nickname: String,
        waitingRoomId: String
    ) {
        showLoadingUseCase()
        logD(
            """
            [fun joinWaitingRoom start]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            webSocketConnectUseCase(
                waitingRoomId = waitingRoomId,
                player = generatePlayerUseCase(nickname = nickname)
            )
            hideLoadingUseCase()
        }
    }

    fun handleEvent(
        createWaitingRoomFailure: () -> Unit,
    ) {
        viewModelScope.launch {
            handleEventUseCase.invoke(
                createWaitingRoomFailure = createWaitingRoomFailure,
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