package com.example.domain.usecase

import com.example.domain.repository.LoadingRepository
import javax.inject.Inject

class ShowLoadingUseCase @Inject constructor(
    private val loadingRepository: LoadingRepository

) {
    operator fun invoke() {
        loadingRepository.show()
    }
}