package com.example.presentation.sharedViewModel

import androidx.lifecycle.viewModelScope
import com.example.data.repositoryImpl.FirebaseRepository
import com.example.domain.model.CreateWaitingRoomData
import com.example.presentation.common.ProjectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitingRoomDataViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ProjectViewModel(tag = "WaitingRoomDataViewModel") {

    private val _waitingRoomData = MutableStateFlow<CreateWaitingRoomData?>(null)
    val waitingRoomData: Flow<CreateWaitingRoomData?> = _waitingRoomData.asStateFlow()

    fun createWaitingRoom(nickname: String) {
        logD("""
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent())
        viewModelScope.launch {
            firebaseRepository.createWaitingRoom(
                nickname = nickname
            ).onSuccess {
                _waitingRoomData.value = it
                logD("""
                    [fun createWaitingRoom success]
                        _waitingRoomData = ${_waitingRoomData.value}
                """.trimIndent())
            }.onFailure {
                logD("[fun createWaitingRoom failure]")
            }
        }
    }

    fun getWaitingRoom(roomId: String) {

    }
}