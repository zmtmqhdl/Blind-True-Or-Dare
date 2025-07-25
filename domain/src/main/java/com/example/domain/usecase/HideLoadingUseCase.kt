package com.example.domain.usecase

import com.example.domain.repository.LoadingRepository
import javax.inject.Inject

class HideLoadingUseCase @Inject constructor(
    private val loadingRepository: LoadingRepository
) {
    suspend operator  fun invoke() {
        loadingRepository.hide()
    }
}