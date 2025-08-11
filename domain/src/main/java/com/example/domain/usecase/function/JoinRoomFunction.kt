package com.example.domain.usecase.function

import com.example.domain.usecase.ConnectWebSocketUseCase
import com.example.domain.usecase.HideLoadingUseCase
import com.example.domain.usecase.SetPlayerUseCase
import com.example.domain.usecase.ShowLoadingUseCase
import com.example.domain.usecase.generatePlayerUseCase
import javax.inject.Inject

class JoinRoomFunction @Inject constructor(
    private val setPlayerUseCase: SetPlayerUseCase,
    private val hideLoadingUseCase: HideLoadingUseCase,
    private val showLoadingUseCase: ShowLoadingUseCase,
    private val connectWebSocketUseCase: ConnectWebSocketUseCase,
){
    suspend operator fun invoke(
        nickname: String,
        roomId: String
    ) {
        showLoadingUseCase()
        val player = generatePlayerUseCase(nickname = nickname)
        setPlayerUseCase(player = player)
        connectWebSocketUseCase(
            roomId = roomId,
            player = player
        )
        hideLoadingUseCase()
    }
}