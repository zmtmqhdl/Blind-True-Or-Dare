package com.example.domain.usecase

import com.example.domain.Event
import com.example.domain.repository.UiFlowRepository
import javax.inject.Inject

class EmitEventUseCase @Inject constructor(
    private val uiFlowRepository: UiFlowRepository
) {
    suspend operator fun invoke(event: Event) {
        uiFlowRepository.emitEvent(event = event)
    }
}