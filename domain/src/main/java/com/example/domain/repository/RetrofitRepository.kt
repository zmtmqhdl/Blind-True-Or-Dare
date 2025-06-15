package com.example.domain.repository

import com.example.domain.common.ApiResponse
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.User
import com.example.domain.model.WaitingRoom


interface RetrofitRepository {
    suspend fun createWaitingRoom(
        user: User,
    ): ApiResponse<CreateWaitingRoom>
}