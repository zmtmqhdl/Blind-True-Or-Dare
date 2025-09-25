package com.example.presentation.content.gameRoom

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.Event
import com.example.domain.model.Answer
import com.example.domain.model.MessageType
import com.example.domain.model.Question
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.DisconnectWebSocketUseCase
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.EventHandlerUseCase
import com.example.domain.usecase.RoomStatusHandlerUseCase
import com.example.domain.usecase.SendMessageUseCase
import com.example.domain.usecase.SetMyAnswerListUseCase
import com.example.domain.usecase.SetMyQuestionListUseCase
import com.example.domain.usecase.WebSocketHandlerUseCase
import com.example.domain.usecase.function.ExitGameFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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
    private val setMyAnswerListUseCase: SetMyAnswerListUseCase,
    private val disconnectWebSocketUseCase: DisconnectWebSocketUseCase,
    private val exitGameFunction: ExitGameFunction,
    private val roomStatusHandlerUseCase: RoomStatusHandlerUseCase
) : ProjectViewModel(
    viewModelTag = "GameRoomViewModel"
) {
    val room = roomRepository.room
    val player = roomRepository.player
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    var timeJob: Job? = null

    private val _time = MutableStateFlow(5L)
    val time: StateFlow<Long> = _time.asStateFlow()

    private val _currentQuestionNumber = MutableStateFlow(1)
    val currentQuestionNumber: StateFlow<Int> = _currentQuestionNumber.asStateFlow()

    private val _isWriteStart = MutableStateFlow(false)
    val isWriteStart: StateFlow<Boolean> = _isWriteStart.asStateFlow()

    private val _isAnswerStart = MutableStateFlow(false)
    val isAnswerStart: StateFlow<Boolean> = _isAnswerStart.asStateFlow()

    private val _myQuestionList: MutableStateFlow<MutableList<Question>> =
        MutableStateFlow(mutableListOf())

    private val _myAnswerList: MutableStateFlow<MutableList<Answer>> =
        MutableStateFlow(mutableListOf())

    fun roomStatusHandler(
        result: suspend () -> Unit
    ) {
        viewModelScope.launch {
            roomStatusHandlerUseCase(
                write = {
                    startWriteQuestion()
                },
                answer = {
                    _currentQuestionNumber.value = 1
                    startAnswerQuestion()
                },
                result = {
                    delay(timeMillis = 3000L)
                    result()
                }
            )
        }
    }


    fun handleWebSocketConnect(
        onDisconnect: () -> Unit
    ) {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                viewModelScope = this,
                onDisconnect = {
                    exitGameFunction()
                    onDisconnect()
                }
            )
        }
    }

    fun startWriteQuestion() {
        timeJob = viewModelScope.launch {
            for (time in 5L downTo 0L) {
                _time.value = time
                if (time != 0L) {
                    delay(timeMillis = 1000L)
                }
            }
            _isWriteStart.value = true
            for (time in room.value!!.writeTime downTo 0L) {
                _time.value = time
                if (time != 0L) {
                    delay(timeMillis = 1000L)
                }
            }
            emitEventUseCase(event = Event.WriteNextQuestion)
        }
    }

    fun startAnswerQuestion() {
        timeJob = viewModelScope.launch {
            for (time in 5L downTo 0L) {
                _time.value = time
                if (time != 0L) {
                    delay(timeMillis = 1000L)
                }
            }
            _isAnswerStart.value = true
            for (time in 30L downTo 0L) {
                _time.value = time
                if (time != 0L) {
                    delay(timeMillis = 1000L)
                }
            }
            emitEventUseCase(event = Event.AnswerNextQuestion)
        }
    }


    fun eventHandler(
        writeNextQuestion: () -> Unit,
        answerNextQuestion: () -> Unit
    ) {
        viewModelScope.launch {
            eventHandlerUseCase(
                viewModelScope = this,
                writeNextQuestion = writeNextQuestion,
                answerNextQuestion = answerNextQuestion
            )
        }
    }

    fun submitQuestion(
        questionValue: String,
    ) {
        _myQuestionList.value.add(
            Question(
                questionId = -1,
                player = player.value!!,
                question = questionValue,
                oVoter = setOf(),
                xVoter = setOf(),
                noAnswer = setOf()
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
            _isWriteStart.value = false
        } else {
            timeJob?.cancel()
            timeJob = viewModelScope.launch {
                for (time in room.value!!.writeTime downTo 0L) {
                    _time.value = time
                    if (time != 0L) {
                        delay(timeMillis = 1000L)
                    }
                }
                emitEventUseCase(event = Event.WriteNextQuestion)
            }
        }
    }

    fun submitAnswer(
        voteValue: Boolean?
    ) {
        _myAnswerList.value.add(
            Answer(
                questionId = room.value!!.questionList[_currentQuestionNumber.value - 1].questionId,
                player = player.value!!,
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
            _isAnswerStart.value = false
        } else {
            timeJob?.cancel()
            timeJob = viewModelScope.launch {
                for (time in 30L downTo 0L) {
                    _time.value = time
                    if (time != 0L) {
                        delay(timeMillis = 1000L)
                    }
                }
                emitEventUseCase(event = Event.AnswerNextQuestion)
            }
        }
    }

    fun exitRoom() {
        disconnectWebSocketUseCase()
    }
}