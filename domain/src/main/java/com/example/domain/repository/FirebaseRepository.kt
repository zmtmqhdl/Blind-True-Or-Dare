package com.example.data.repositoryImpl

import com.example.domain.model.CreateWaitingRoomData
import com.example.domain.model.WaitingRoomData

interface FirebaseRepository {
    suspend fun createWaitingRoom(nickname: String): Result<CreateWaitingRoomData>
    suspend fun getWaitingRoom(roomId: String, update: (WaitingRoomData?) -> Unit)

}