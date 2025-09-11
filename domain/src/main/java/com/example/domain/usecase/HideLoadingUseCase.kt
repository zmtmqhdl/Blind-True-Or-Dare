package com.example.domain.usecase

import com.example.domain.repository.UiFlowRepository
import javax.inject.Inject

class HideLoadingUseCase @Inject constructor(
    private val uiFlowRepository: UiFlowRepository
) {
    suspend operator  fun invoke() {
        uiFlowRepository.hide()
    }
}