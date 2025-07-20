package com.example.domain.usecase

import com.example.domain.Event
import com.example.domain.repository.LoadingRepository
import javax.inject.Inject

class HandleEventUseCase @Inject constructor(
    private val loadingRepository: LoadingRepository
) {
    suspend operator fun invoke(
        createWaitingRoomFailure: () -> Unit = {},
    ) {
        loadingRepository.event.collect {
            when (it) {
                is Event.CreateWaitingRoomFailure -> {
                    createWaitingRoomFailure()
                }
            }
        }
    }
}