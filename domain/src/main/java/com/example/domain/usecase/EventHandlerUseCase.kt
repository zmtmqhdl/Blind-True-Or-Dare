package com.example.domain.usecase

import com.example.domain.Event
import com.example.domain.repository.UiFlowRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventHandlerUseCase @Inject constructor(
    private val uiFlowRepository: UiFlowRepository
) {
    var job: Job? = null
    operator fun invoke(
        viewModelScope: CoroutineScope,
        createRoomFailure: () -> Unit = {},
        writeNextQuestion: () -> Unit = {},
        answerNextQuestion: () -> Unit = {},
        startFailure: () -> Unit = {},
        webSocketRejoin: () -> Unit = {}
    ) {
        job?.cancel()
        job = viewModelScope.launch {
            uiFlowRepository.event.collect {
                when (it) {
                    is Event.CreateRoomFailure -> createRoomFailure()
                    is Event.WriteNextQuestion -> writeNextQuestion()
                    is Event.AnswerNextQuestion -> answerNextQuestion()
                    is Event.StartFailure -> startFailure()
                    is Event.WebSocketRejoin -> webSocketRejoin()
                }
            }
        }

    }
}