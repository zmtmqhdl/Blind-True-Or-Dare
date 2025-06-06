package com.example.data.repositoryImpl

import android.util.Log
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
                val data = snapshot.getValue(WaitingRoomData::class.java)
                update(data)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        )
    }
}