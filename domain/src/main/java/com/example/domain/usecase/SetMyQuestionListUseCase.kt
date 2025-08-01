package com.example.domain.usecase

import com.example.domain.model.Question
import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class SetMyQuestionListUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        myQuestionList: List<Question>
    ) {
        roomRepository.setMyQuestionList(myQuestionList = myQuestionList)
    }
}