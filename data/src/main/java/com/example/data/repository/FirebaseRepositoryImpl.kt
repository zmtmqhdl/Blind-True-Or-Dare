package com.example.data.repository

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class FirebaseRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : FirebaseRepository {

    override suspend fun createWaitingRoom() {
        withContext(Dispatchers.IO) {
            val roomId = UUID.randomUUID().toString()
            val roomRef = database.getReference("waitingRooms").child(roomId)

            val roomData = mapOf(
                "createdAt" to System.currentTimeMillis(),
                "status" to "waiting"
            )

            roomRef.setValue(roomData).await()
        }
    }
}