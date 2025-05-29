package com.example.data.repositoryImpl

import com.example.domain.model.CreateWaitingRoomData
import com.example.domain.model.Status
import com.example.domain.model.User
import com.example.domain.model.WaitingRoomData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : FirebaseRepository {

    override suspend fun createWaitingRoom(nickname: String): Result<CreateWaitingRoomData> =
        runCatching {
            val roomId = UUID.randomUUID().toString()
            val userId = UUID.randomUUID().toString()
            val reference = firebaseDatabase.getReference("waitingRoom").child(roomId)

            val waitingRoomData = WaitingRoomData(
                status = Status.WAITING,
                participantList = listOf(User(id = userId, nickname = nickname))
            )

            reference.setValue(waitingRoomData).await()

            CreateWaitingRoomData(roomId = roomId, userId = userId)
        }

    override suspend fun getWaitingRoom(roomId: String, update: (WaitingRoomData?) -> Unit) {
        val reference = firebaseDatabase.getReference("waitingRoom").child(roomId)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(WaitingRoomData::class.java)
                update(data)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        )
    }
}