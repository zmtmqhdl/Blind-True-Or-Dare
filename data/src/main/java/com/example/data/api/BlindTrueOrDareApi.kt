package com.example.data.api

import com.example.data.model.CreateRoomRequestDto
import com.example.data.model.CreateRoomDto
import retrofit2.http.Body
import retrofit2.http.POST

interface BlindTrueOrDareApi {
    @POST("/room/create")
    suspend fun createRoom(
        @Body createRoomRequestDto: CreateRoomRequestDto
    ): CreateRoomDto
}