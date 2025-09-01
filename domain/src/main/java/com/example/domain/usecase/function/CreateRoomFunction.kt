package com.example.domain.usecase.function

import android.util.Log
import com.example.domain.Event
import com.example.domain.common.onFailure
import com.example.domain.common.onSuccess
import com.example.domain.usecase.ConnectWebSocketUseCase
import com.example.domain.usecase.CreateRoomUseCase
import com.example.domain.usecase.EmitEventUseCase
import com.example.domain.usecase.HideLoadingUseCase
import com.example.domain.usecase.SetPlayerUseCase
import com.example.domain.usecase.ShowLoadingUseCase
import com.example.domain.usecase.generatePlayerUseCase
import javax.inject.Inject

class   CreateRoomFunction @Inject constructor(
    private val createRoomUseCase: CreateRoomUseCase,
    private val hideLoadingUseCase: HideLoadingUseCase,
    private val showLoadingUseCase: ShowLoadingUseCase,
    private val setPlayerUseCase: SetPlayerUseCase,
    private val connectWebSocketUseCase: ConnectWebSocketUseCase,
    private val emitEventUseCase: EmitEventUseCase
) {
    suspend operator fun invoke(
        nickname: String
    ) {
        showLoadingUseCase()
        val player = generatePlayerUseCase(nickname = nickname)
        createRoomUseCase(
            player = player
        ).onSuccess {
            setPlayerUseCase(player = player)
            connectWebSocketUseCase(
                roomId = it.roomId,
                player = player
            )
            hideLoadingUseCase()
        }.onFailure {
            hideLoadingUseCase()
            emitEventUseCase(event = Event.CreateRoomFailure(error = it))
        }
    }
}