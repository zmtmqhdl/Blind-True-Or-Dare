package com.example.presentation.gameRoom

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.Event
import com.example.domain.model.Answer
import com.example.domain.model.MessageType
import com.example.domain.model.Question
import com.example.domain.model.RoomStatus
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.SendMessageUseCase
import com.example.domain.usecase.SetMyAnswerListUseCase
import com.example.domain.usecase.SetMyQuestionListUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameRoomViewModel @Inject constructor(
    roomRepository: RoomRepository,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val emitEventUseCase: EmitEventUseCase,
    private val eventHandlerUseCase: EventHandlerUseCase,
    private val setMyQuestionListUseCase: SetMyQuestionListUseCase,
    private val setMyAnswerListUseCase: SetMyAnswerListUseCase
) : ProjectViewModel(
    viewModelTag = "GameRoomViewModel"
) {
    val room = roomRepository.room
    val player = roomRepository.player
    var timeJob: Job? = null

    private val _time = MutableStateFlow(30 * 1000L)
    val time: StateFlow<Long> = _time.asStateFlow()

    private val _currentQuestionNumber = MutableStateFlow(1)
    val currentQuestionNumber: StateFlow<Int> = _currentQuestionNumber.asStateFlow()

    private val _myQuestionList: MutableStateFlow<MutableList<Question>> =
        MutableStateFlow(mutableListOf())

    private val _myAnswerList: MutableStateFlow<MutableList<Answer>> =
        MutableStateFlow(mutableListOf())

    init {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                onDisconnect = {}
            )
        }
    }

    fun eventHandler(
        writeNextQuestion: () -> Unit,
        answerNextQuestion: () -> Unit
    ) {
        viewModelScope.launch {
            eventHandlerUseCase(
                writeNextQuestion = writeNextQuestion,
                answerNextQuestion = answerNextQuestion
            )
        }
    }

    fun submitQuestion(
        questionValue: String,
        voteValue: Boolean
    ) {
        if (room.value?.roomStatus == RoomStatus.WRITE) {
            _myQuestionList.value.add(
                Question(
                    questionId = -1,
                    playerId = player.value!!.playerId,
                    question = questionValue,
                    oVoters = setOf(),
                    xVoters = setOf()
                )
            )
            _currentQuestionNumber.value += 1
            if (_currentQuestionNumber.value > room.value?.questionNumber!!) {
                setMyQuestionListUseCase(myQuestionList = _myQuestionList.value)
                sendMessageUseCase(
                    messageType = MessageType.SEND_WRITE_END,
                    data = _myQuestionList.value
                )
                timeJob?.cancel()
                _currentQuestionNumber.value = 1
            } else {
                timeJob?.cancel()
                timeJob = viewModelScope.launch {
                    for (time in 30 downTo 0) {
                        _time.value = time * 1000L
                        if (time != 0) {
                            delay(timeMillis = 1000L)
                        }
                    }
                    emitEventUseCase(event = Event.WriteNextQuestion)
                }
            }
        } else if (room.value?.roomStatus == RoomStatus.ANSWER) {
            _myAnswerList.value.add(
                Answer(
                    questionId = room.value!!.questionList[_currentQuestionNumber.value - 1].questionId,
                    playerId = player.value!!.playerId,
                    answer = voteValue
                )
            )
            _currentQuestionNumber.value += 1
            if (_currentQuestionNumber.value > room.value?.questionList!!.size) {
                setMyAnswerListUseCase(myAnswerList = _myAnswerList.value)
                sendMessageUseCase(
                    messageType = MessageType.SEND_ANSWER_END,
                    data = _myAnswerList.value
                )
                timeJob?.cancel()
            } else {
                timeJob?.cancel()
                timeJob = viewModelScope.launch {
                    for (time in 30 downTo 0) {
                        _time.value = time * 1000L
                        if (time != 0) {
                            delay(timeMillis = 1000L)
                        }
                    }
                    emitEventUseCase(event = Event.AnswerNextQuestion)
                }
            }

        }

    }
}