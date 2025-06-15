package com.example.domain.repository

import com.example.domain.model.WaitingRoom

interface FirebaseRepository {
    suspend fun createWaitingRoom(waitingRoom: WaitingRoom): Result<WaitingRoom>
    suspend fun getWaitingRoom(roomId: String, update: (WaitingRoom?) -> Unit)

}