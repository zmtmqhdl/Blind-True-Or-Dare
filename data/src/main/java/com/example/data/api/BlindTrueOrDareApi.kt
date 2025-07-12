package com.example.data.api

import com.example.data.model.CreateWaitingRoomRequestDto
import com.example.data.model.CreateWaitingRoomDto
import retrofit2.http.Body
import retrofit2.http.POST

interface BlindTrueOrDareApi {
    @POST("/waitingRoom/create")
    suspend fun createWaitingRoom(
        @Body createWaitingRoomRequestDto: CreateWaitingRoomRequestDto
    ): CreateWaitingRoomDto
}