package com.example.data.repositoryImpl

import com.example.data.mapper.toDomain
import com.example.data.model.WaitingRoomDataDto
import com.example.domain.model.WaitingRoom
import com.example.domain.repository.FirebaseRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// todo - dto 정리해야댐
class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : FirebaseRepository {

    override suspend fun createWaitingRoom(waitingRoom: WaitingRoom): Result<WaitingRoom> =
        runCatching {
            val reference = firebaseDatabase.getReference("waitingRoom").child(waitingRoom.waitingRoomId)

            reference.setValue(waitingRoom).await()

            waitingRoom
        }

    override suspend fun getWaitingRoom(roomId: String, update: (WaitingRoom?) -> Unit) {
        val reference = firebaseDatabase.getReference("waitingRoom").child(roomId)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(WaitingRoomDataDto::class.java)
                update(data?.toDomain())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        )
    }
}