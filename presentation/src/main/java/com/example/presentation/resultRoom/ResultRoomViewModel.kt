package com.example.presentation.resultRoom

import com.example.core.core.ProjectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultRoomViewModel @Inject
constructor(): ProjectViewModel(
    viewModelTag = "ResultRoomViewModel"
) {
}