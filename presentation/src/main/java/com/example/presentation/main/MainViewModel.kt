package com.example.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.core.core.ProjectViewModel
import com.example.data.client.WebSocketManager
import com.example.data.model.MessageType
import com.example.data.model.WaitingRoomDto
import com.example.data.toDomain
import com.example.domain.common.onFailure
import com.example.domain.common.onSuccess
import com.example.domain.model.Player
import com.example.domain.repository.LoadingRepository
import com.example.domain.repository.WaitingRoomRepository
import com.example.presentation.util.IdGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.serialization.json.Json

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadingRepository: LoadingRepository,
    private val waitingRoomRepository: WaitingRoomRepository
) : ProjectViewModel<MainState, MainEvent>(
    initialState = MainState(),
    viewModelTag = "MainViewModel"
) {
    init {
        WebSocketManager.addListener { message ->
            when (message.type) {
                MessageType.Update -> {
                    waitingRoomRepository.saveWaitingRoom(
                        waitingRoom = Json.decodeFromString<WaitingRoomDto>(message.data!!).toDomain()
                    )
                }
            }
        }
    }


    fun createWaitingRoom(
        nickname: String
    ) {
        loadingRepository.show()
        logD(
            """
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            val player = Player(
                playerId = IdGenerator(),
                nickname = nickname
            )
            waitingRoomRepository.createWaitingRoom(
                player = player
            ).onSuccess {
                WebSocketManager.connect(
                    waitingRoomId = it.waitingRoomId,
                    player = player,
                    onSuccess = {
                        viewModelScope.launch {
                            loadingRepository.hide()
                            setEvent(event = MainEvent.CreateWaitingRoomSuccess)
                            logD("[fun createWaitingRoom success]")
                        }
                    },
                    onFailure = {
                        viewModelScope.launch {
                            loadingRepository.hide()
                            setEvent(event = MainEvent.CreateWaitingRoomFailure)
                            logD("[fun createWaitingRoom failure]")
                        }
                    }
                )
            }.onFailure {
                viewModelScope.launch {
                    loadingRepository.hide()
                    setEvent(event = MainEvent.CreateWaitingRoomFailure)
                    logD("[fun createWaitingRoom failure]")
                }
            }
        }
    }

    fun joinWaitingRoom(
        nickname: String,
        waitingRoomId: String
    ) {
        loadingRepository.show()
        logD("""
            [fun joinWaitingRoom start]
                nickname = $nickname
        """.trimIndent())
        viewModelScope.launch {
            val player = Player(
                playerId = IdGenerator(),
                nickname = nickname
            )
            WebSocketManager.connect(
                waitingRoomId = waitingRoomId,
                player = player,
                onSuccess = {
                    viewModelScope.launch {
                        loadingRepository.hide()
                        setEvent(event = MainEvent.JoinWaitingRoomSuccess)
                    }
                },
                onFailure = {
                    viewModelScope.launch {
                        loadingRepository.hide()
                        setEvent(event = MainEvent.JoinWaitingRoomFailure)
                    }
                },
            )
        }
    }
}

data class MainState(
    val loading: Boolean = false,
)

sealed class MainEvent {
    object CreateWaitingRoomSuccess : MainEvent()
    object CreateWaitingRoomFailure : MainEvent()
    object JoinWaitingRoomSuccess: MainEvent()
    object JoinWaitingRoomFailure: MainEvent()
}
