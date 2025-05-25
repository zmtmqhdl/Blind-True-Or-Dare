package com.example.data.repositoryImpl

import com.example.domain.model.CreateWaitingRoomData

interface FirebaseRepository {
    suspend fun createWaitingRoom(nickname: String): CreateWaitingRoomData
}