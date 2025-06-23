package com.example.data.repositoryImpl

import com.example.data.api.RetrofitApi
import com.example.data.mapper.toDomain
import com.example.data.mapper.toDto
import com.example.data.model.CreateWaitingRoomRequest
import com.example.domain.common.ApiResponse
import com.example.domain.common.request
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.Player
import com.example.domain.repository.RetrofitRepository

class RetrofitRepositoryImpl(
    private val api: RetrofitApi
) : RetrofitRepository {

    override suspend fun createWaitingRoom(
        player: Player,
    ): ApiResponse<CreateWaitingRoom> =
        request {
            api.createWaitingRoom(
                createWaitingRoomRequest = CreateWaitingRoomRequest(
                    player = player.toDto(),
                )
            ).toDomain()
        }
}