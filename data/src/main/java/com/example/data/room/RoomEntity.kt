package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
)