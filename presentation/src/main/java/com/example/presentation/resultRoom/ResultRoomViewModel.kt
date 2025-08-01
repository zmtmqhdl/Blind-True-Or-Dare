package com.example.presentation.resultRoom

import com.example.core.core.ProjectViewModel
import com.example.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultRoomViewModel @Inject
constructor(
    private val roomRepository: RoomRepository
): ProjectViewModel(
    viewModelTag = "ResultRoomViewModel"
) {
    val room = roomRepository.room
    val myQuestionList = roomRepository.myQuestionList
    val myAnswerListUseCase = roomRepository.myAnswerList


}