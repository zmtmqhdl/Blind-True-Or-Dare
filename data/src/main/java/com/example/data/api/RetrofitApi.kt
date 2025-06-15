package com.example.data.api

import com.example.data.model.CreateWaitingRoomRequest
import com.example.data.model.CreateWaitingRoomResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApi {
    @POST("/waitingRoom/create")
    suspend fun createWaitingRoom(
        @Body createWaitingRoomRequest: CreateWaitingRoomRequest
    ): CreateWaitingRoomResponse

}