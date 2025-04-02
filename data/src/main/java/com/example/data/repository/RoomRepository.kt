package com.example.data.repository

import com.example.data.room.RoomEntity

interface RoomRepository {
    suspend fun insertUser(user: RoomEntity)
    suspend fun getUserById(id: Int): RoomEntity?
}