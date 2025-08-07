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
    val myAnswerList = roomRepository.myAnswerList

    // 질문의 정렬? ox보여주고 내질문 및 답변에 대한건 따로 표기 (별표..?)

    init {


    }
}