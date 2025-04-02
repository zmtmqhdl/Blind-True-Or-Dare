package com.example.data.repository

import com.example.data.room.RoomDao
import com.example.data.room.RoomEntity
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val dao: RoomDao) : RoomRepository {
    override suspend fun insertUser(user: RoomEntity) {
        dao.insert(user)
    }

    override suspend fun getUserById(id: Int): RoomEntity? {
        return dao.getUserById(id)
    }

}