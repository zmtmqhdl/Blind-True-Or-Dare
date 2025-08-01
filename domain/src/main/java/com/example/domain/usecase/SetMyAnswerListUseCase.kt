package com.example.domain.usecase

import com.example.domain.model.Answer
import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class SetMyAnswerListUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        myAnswerList: List<Answer>
    ) {
        roomRepository.setMyAnswerList(myAnswerList = myAnswerList)
    }
}