package com.example.presentation.content

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.repository.UiFlowRepository
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.ExitGameFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    uiFlowRepository: UiFlowRepository,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val exitGameFunction: ExitGameFunction,

): ProjectViewModel(
    viewModelTag = "ContentViewModel"
) {
    val loading = uiFlowRepository.loading


    fun handleWebSocketConnect(
        onDisconnect: () -> Unit
    ) {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                viewModelScope = this,
                onDisconnect = {
                    exitGameFunction()
                    onDisconnect()
                },
                onConnectFailure = { logD("$it") },
            )
        }
    }
}