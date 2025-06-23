package com.example.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.domain.common.onFailure
import com.example.domain.common.onSuccess
import com.example.domain.model.Player
import com.example.domain.repository.RetrofitRepository
import com.example.presentation.core.ProjectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository
) : ProjectViewModel<MainState, MainEvent>(
    initialState = MainState(),
    viewModelTag = "MainViewModel"
) {
    val isLoading: StateFlow<Boolean> = state
        .map { it.loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    fun createWaitingRoom(nickname: String) {
        setState { copy(loading = true) }
        logD(
            """
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            val playerId = UUID.randomUUID().toString()
            retrofitRepository.createWaitingRoom(
                player = Player(
                    playerId = playerId,
                    nickname = nickname
                )
            ).onSuccess {
                setState { copy(loading = false) }
                setEvent(
                    event = MainEvent.CreateWaitingRoomSuccess(
                        waitingRoomId = it.waitingRoomId,
                        playerId = playerId
                    )
                )
                logD("[fun createWaitingRoom success]")
            }.onFailure {
                setState { copy(loading = false) }
                setEvent(event = MainEvent.CreateWaitingRoomFailure)
                logD("[fun createWaitingRoom failure]")
            }
        }
    }

    fun getWaitingRoom(roomId: String) {

    }
}

data class MainState(
    val loading: Boolean = false,
)

sealed class MainEvent {
    object Idle : MainEvent()
    class CreateWaitingRoomSuccess(
        val waitingRoomId: String,
        val playerId: String
    ) : MainEvent()
    object CreateWaitingRoomFailure : MainEvent()
    class ShowError(val message: String) : MainEvent()
}
