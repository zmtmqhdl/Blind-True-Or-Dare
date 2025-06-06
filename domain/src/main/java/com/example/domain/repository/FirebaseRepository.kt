package com.example.data.repositoryImpl

import com.example.domain.model.WaitingRoomData

interface FirebaseRepository {
    suspend fun createWaitingRoom(waitingRoomData: WaitingRoomData): Result<WaitingRoomData>
    suspend fun getWaitingRoom(roomId: String, update: (WaitingRoomData?) -> Unit)

}