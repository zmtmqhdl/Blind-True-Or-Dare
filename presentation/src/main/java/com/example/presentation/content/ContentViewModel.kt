package com.example.presentation.content

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.repository.NetworkRepository
import com.example.domain.repository.RoomRepository
import com.example.domain.repository.UiFlowRepository
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.ExitGameFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val exitGameFunction: ExitGameFunction,
    uiFlowRepository: UiFlowRepository,
    networkRepository: NetworkRepository,
    roomRepository: RoomRepository
) : ProjectViewModel(
    viewModelTag = "ContentViewModel"
) {
    val loading = uiFlowRepository.loading
    val isNetworkAvailable = networkRepository.isNetworkAvailable
    val room = roomRepository.room

    private val _isNetworkAvailableDialog = MutableStateFlow(false)
    val isNetworkAvailableDialog: StateFlow<Boolean> = _isNetworkAvailableDialog.asStateFlow()

    private val _reconnectTimer = MutableStateFlow(10)
    val reconnectTimer: StateFlow<Int> = _reconnectTimer.asStateFlow()

    private val _resetRoom = MutableStateFlow(false)
    val resetRoom: StateFlow<Boolean> = _resetRoom.asStateFlow()

    private var reconnectTimerJob: Job? = null

    init {
        viewModelScope.launch {
            isNetworkAvailable.collect {
                if (it) {
                    _isNetworkAvailableDialog.value = false
                    reconnectTimerJob?.cancel()
                    _reconnectTimer.value = 10
                    logD("네트워크 연결됨")
                } else {
                    reconnectTimerJob?.cancel()
                    if (room.value != null) {
                        _isNetworkAvailableDialog.value = true
                        reconnectTimerJob = startReconnectTimer()
                        logD("네트워크 끊김 감지")
                    }
                }
            }
        }
    }

    fun handleWebSocketConnect(
        onDisconnect: () -> Unit
    ) {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                viewModelScope = this,
                onDisconnect = {
                    if (isNetworkAvailable.value) {
                        exitGameFunction()
                        onDisconnect()
                    } else {
                        if (_resetRoom.value) {
                            exitGameFunction()
                            onDisconnect()
                            _resetRoom.value = false
                        }
                    }
                },
                onConnectFailure = { logD("$it") },
            )
        }
    }

    private fun startReconnectTimer() =
        viewModelScope.launch {
            for (i in 10 downTo 0) {
                _reconnectTimer.value = i
                delay(1000L)
            }
            delay(1000L)
           _resetRoom.value = true
        }


    fun dismissIsNetworkAvailableDialog() {
        _isNetworkAvailableDialog.value = false
        exitGameFunction()
    }
}