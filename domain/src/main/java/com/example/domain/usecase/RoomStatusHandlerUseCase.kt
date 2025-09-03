package com.example.domain.usecase

import com.example.domain.model.RoomStatus
import com.example.domain.repository.RoomRepository
import javax.inject.Inject

class RoomStatusHandlerUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(
        write: suspend () -> Unit = {},
        answer: suspend () -> Unit = {},
        result: suspend () -> Unit = {}
    ) {
        roomRepository.room.collect {
            when (it?.roomStatus) {
                RoomStatus.WRITE -> {
                    write()
                }
                RoomStatus.ANSWER -> {
                    answer()
                }
                RoomStatus.RESULT -> {
                    result()
                }
                else -> {}
            }
        }
    }
}