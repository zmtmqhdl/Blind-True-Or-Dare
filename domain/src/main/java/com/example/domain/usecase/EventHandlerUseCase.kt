package com.example.domain.usecase

import com.example.domain.Event
import com.example.domain.repository.UiFlowRepository
import javax.inject.Inject

class EventHandlerUseCase @Inject constructor(
    private val uiFlowRepository: UiFlowRepository
) {
    suspend operator fun invoke(
        createRoomFailure: () -> Unit = {},
        writeNextQuestion: () -> Unit = {},
        answerNextQuestion: () -> Unit = {},
        startFailure: () -> Unit = {},
        webSocketRejoin: () -> Unit = {}
    ) {
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