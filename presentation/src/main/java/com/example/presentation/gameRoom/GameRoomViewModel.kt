package com.example.presentation.gameRoom

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.model.Question
import com.example.domain.repository.WaitingRoomRepository
import com.example.domain.usecase.WebSocketHandlerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameRoomViewModel @Inject constructor(
    waitingRoomRepository: WaitingRoomRepository,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
): ProjectViewModel(
    viewModelTag = "GameRoomViewModel"
){
    val player = waitingRoomRepository.player

    val questionSetting = waitingRoomRepository.questionSetting
    private val _myQuestionList: MutableStateFlow<MutableList<Question>> = MutableStateFlow(mutableListOf())
    val myQuestionList: StateFlow<MutableList<Question>> = _myQuestionList.asStateFlow()

    init {
        viewModelScope.launch {
            webSocketHandlerUseCase(
                onDisconnect = {}
            )
        }
    }

    fun addQuestion(
        question: String,
        oCount: Int,
        xCount: Int
    ) {
        _myQuestionList.value.add(
            Question(
                playerId = player.value!!.playerId,
                question = question,
                oCount = oCount,
                xCount = xCount
            )
        )
    }

}