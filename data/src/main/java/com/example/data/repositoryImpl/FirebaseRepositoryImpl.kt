package com.example.data.repositoryImpl

import com.example.domain.model.CreateWaitingRoomData
import com.example.domain.model.Status
import com.example.domain.model.User
import com.example.domain.model.WaitingRoomData
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : FirebaseRepository {

    override suspend fun createWaitingRoom(nickname: String): CreateWaitingRoomData {
        val roomId = UUID.randomUUID().toString()
        val userId = UUID.randomUUID().toString()
        val reference = firebaseDatabase.getReference("waitingRoom").child(roomId)

        val waitingRoomData = WaitingRoomData(
            status = Status.WAITING,
            participantList = listOf(
                User(
                    id = userId,
                    nickname = nickname)
            )
        )

        reference.setValue(waitingRoomData).await()

        return CreateWaitingRoomData(
            roomId = roomId,
            userId = userId
        )
    }
}