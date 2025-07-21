package com.example.domain.usecase

import com.example.domain.Event
import com.example.domain.repository.LoadingRepository
import javax.inject.Inject

class EmitEventUseCase @Inject constructor(
    private val loadingRepository: LoadingRepository
) {
    operator suspend fun invoke(event: Event) {
        loadingRepository.emitEvent(event = event)
    }
}