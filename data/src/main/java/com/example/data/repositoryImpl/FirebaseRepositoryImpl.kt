package com.example.data.repositoryImpl

import com.example.data.mapper.toDomain
import com.example.data.model.WaitingRoomDataDto
import com.example.domain.model.WaitingRoomData
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

    override suspend fun createWaitingRoom(waitingRoomData: WaitingRoomData): Result<WaitingRoomData> =
        runCatching {
            val reference = firebaseDatabase.getReference("waitingRoom").child(waitingRoomData.roomId)

            reference.setValue(waitingRoomData).await()

            waitingRoomData
        }

    override suspend fun getWaitingRoom(roomId: String, update: (WaitingRoomData?) -> Unit) {
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