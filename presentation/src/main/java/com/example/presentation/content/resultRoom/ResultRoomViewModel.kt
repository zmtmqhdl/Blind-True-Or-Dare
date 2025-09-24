package com.example.presentation.content.resultRoom

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.model.MessageType
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.DisconnectWebSocketUseCase
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.MessageHandlerUseCase
import com.example.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultRoomViewModel @Inject
constructor(
    private val roomRepository: RoomRepository,
    private val disconnectWebSocketUseCase: DisconnectWebSocketUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val eventHandlerUseCase: EventHandlerUseCase
): ProjectViewModel(
    viewModelTag = "ResultRoomViewModel"
) {
    val room = roomRepository.room
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )
    val myQuestionList = roomRepository.myQuestionList
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )
    val myAnswerList = roomRepository.myAnswerList
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    // 질문의 정렬? ox보여주고 내질문 및 답변에 대한건 따로 표기 (별표..?)


    fun exitRoom() {
        viewModelScope.launch {
            disconnectWebSocketUseCase()
        }
    }

    fun rejoinRoom() {
        viewModelScope.launch {
            sendMessageUseCase(
                messageType = MessageType.REJOIN
            )
        }
    }

    fun eventHandler(
        webSocketRejoin: () -> Unit
    ) {
        viewModelScope.launch {
            eventHandlerUseCase(
                webSocketRejoin = webSocketRejoin
            )
        }
    }
}
