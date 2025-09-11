package com.example.domain.usecase

import com.example.domain.Event
import com.example.domain.model.MessageType
import com.example.domain.repository.WebSocketRepository
import javax.inject.Inject

class MessageHandlerUseCase @Inject constructor(
    private val webSocketRepository: WebSocketRepository,
    private val setRoomUseCase: SetRoomUseCase,
    private val emitEventUseCase: EmitEventUseCase
){
    suspend operator fun invoke() {
        webSocketRepository.message.collect {
            when (it?.type) {
                MessageType.UPDATE -> {
                    setRoomUseCase(
                        data = it.data!!
                    )
                }
                MessageType.REJOIN -> {
                    setRoomUseCase(
                        data = it.data!!
                    )
                    emitEventUseCase(event = Event.WebSocketRejoin)
                }


                else -> {

                }
            }
        }
    }
}