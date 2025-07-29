package com.example.presentation.gameRoom

import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.Event
import com.example.domain.model.MessageType
import com.example.domain.model.Question
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.SendMessageUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameRoomViewModel @Inject constructor(
    roomRepository: RoomRepository,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val emitEventUseCase: EmitEventUseCase
): ProjectViewModel(
    viewModelTag = "GameRoomViewModel"
){
    val room = roomRepository.room
    val player = roomRepository.player
    var timeJob: Job? = null

    private val _time = MutableStateFlow(30 * 1000L)
    val time: StateFlow<Long> = _time.asStateFlow()

    private val _currentQuestionNumber = MutableStateFlow(1)
    val currentQuestionNumber: StateFlow<Int> = _currentQuestionNumber.asStateFlow()

    private val _myQuestionList: MutableStateFlow<MutableList<Question>> = MutableStateFlow(mutableListOf())
    val myQuestionList: StateFlow<MutableList<Question>> = _myQuestionList.asStateFlow()

    init {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                onDisconnect = {}
            )
        }
    }

    fun startWriteTimer() {
        timeJob?.cancel()
        timeJob = viewModelScope.launch {
            for (time in 30 downTo 0) {
                _time.value = time * 1000L
                if (time != 0) {
                    delay(timeMillis = 1000L)
                }
                emitEventUseCase(event = Event.WriteNextQuestion)
            }
        }
    }

    fun addQuestion(
        question: String,
    ) {
        _myQuestionList.value.add(
            Question(
                playerId = player.value!!.playerId,
                question = question,
                oCount = 0,
                xCount = 0
            )
        )
    }

    fun sendQuestion() {
        sendMessageUseCase(
            messageType = MessageType.SEND_WRITE_END,
            data = _myQuestionList.value
        )
    }



}