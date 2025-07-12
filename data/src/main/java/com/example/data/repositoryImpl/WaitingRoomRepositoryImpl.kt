package com.example.data.repositoryImpl

import android.graphics.Bitmap
import com.example.data.api.BlindTrueOrDareApi
import com.example.data.toDomain
import com.example.data.toDto
import com.example.domain.common.ApiResponse
import com.example.domain.common.request
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.CreateWaitingRoomRequest
import com.example.domain.model.Player
import com.example.domain.model.WaitingRoom
import com.example.domain.repository.WaitingRoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WaitingRoomRepositoryImpl(
    private val api: BlindTrueOrDareApi
) : WaitingRoomRepository {

    private val _waitingRoom = MutableStateFlow<WaitingRoom?>(null)
    override val waitingRoom: StateFlow<WaitingRoom?> = _waitingRoom.asStateFlow()

    private val _barCode = MutableStateFlow<Bitmap?>(null)
    override val barCode: StateFlow<Bitmap?> = _barCode.asStateFlow()

    override suspend fun createWaitingRoom(
        player: Player,
    ): ApiResponse<CreateWaitingRoom> =
        request {
            api.createWaitingRoom(
                createWaitingRoomRequestDto = CreateWaitingRoomRequest(
                    player = player
                ).toDto()
            ).toDomain()
        }
}