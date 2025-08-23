package com.example.domain.usecase.function

import com.example.domain.Event
import com.example.domain.model.MessageType
import com.example.domain.repository.RoomRepository
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.SendMessageUseCase
import javax.inject.Inject

class GameStartFunction @Inject constructor(
    private val emitEventUseCase: EmitEventUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke() {
        if (roomRepository.room.value!!.participantList.size > 1) {
            sendMessageUseCase(
                messageType = MessageType.SEND_START
            )
        } else {
            emitEventUseCase(event = Event.StartFailure)
        }
    }
}