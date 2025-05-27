package com.example.presentation.sharedViewModel

import androidx.lifecycle.viewModelScope
import com.example.data.repositoryImpl.FirebaseRepository
import com.example.presentation.common.MyWorldViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitingRoomDataViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : MyWorldViewModel(tag = "WaitingRoomDataViewModel") {



    fun createWaitingRoom(nickname: String) {
        logD("""
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent())
        viewModelScope.launch {
            firebaseRepository.createWaitingRoom(
                nickname = nickname
            ).onSuccess {
                logD("""
                    [fun createWaitingRoom success]
                        
                """.trimIndent())
            }.onFailure {
                logD("""
                    [fun createWaitingRoom failure]
                        
                """.trimIndent())
            }
        }
    }
}