package com.example.data.repository

import com.example.domain.model.Status
import com.example.domain.model.User
import com.example.domain.model.WaitingRoomData
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class FirebaseRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : FirebaseRepository {

    override suspend fun createWaitingRoom(nickname: String) {
        withContext(Dispatchers.IO) {
            val roomId = UUID.randomUUID().toString()
            val userId = UUID.randomUUID().toString()
            val roomRef = database.getReference("waitingRooms").child(roomId)

            val waitingRoomData = WaitingRoomData(
                status = Status.WAITING,
                participantList = listOf(
                    User(userId, nickname)
                )
            )

            roomRef.setValue(waitingRoomData).await()
        }
    }
}