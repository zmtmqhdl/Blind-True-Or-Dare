package com.example.presentation.questionRoom

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.domain.repository.WaitingRoomRepository
import com.example.domain.usecase.WebSocketHandlerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionRoomViewModel @Inject constructor(
    waitingRoomRepository: WaitingRoomRepository,
    private val webSocketHandlerUseCase: WebSocketHandlerUseCase,
): ProjectViewModel(
    viewModelTag = "QuestionRoomViewModel"
){
    init {
        viewModelScope.launch {
            webSocketHandlerUseCase()
        }
    }

    val questionRoomSetting = waitingRoomRepository.questionRoomSetting
}