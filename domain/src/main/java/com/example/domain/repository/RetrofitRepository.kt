package com.example.domain.repository

import com.example.domain.common.ApiResponse
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.Player


interface RetrofitRepository {
    suspend fun createWaitingRoom(
        player: Player,
    ): ApiResponse<CreateWaitingRoom>
}