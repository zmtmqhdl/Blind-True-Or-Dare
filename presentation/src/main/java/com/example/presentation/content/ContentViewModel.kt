package com.example.presentation.content

import com.example.core.core.ProjectViewModel
import com.example.domain.repository.LoadingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    loadingRepository: LoadingRepository
): ProjectViewModel(
    viewModelTag = "ContentViewModel"
) {
    val loading = loadingRepository.loading
}