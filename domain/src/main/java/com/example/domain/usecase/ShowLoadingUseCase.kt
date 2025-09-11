package com.example.domain.usecase

import com.example.domain.repository.UiFlowRepository
import javax.inject.Inject

class ShowLoadingUseCase @Inject constructor(
    private val uiFlowRepository: UiFlowRepository

) {
    operator fun invoke() {
        uiFlowRepository.show()
    }
}