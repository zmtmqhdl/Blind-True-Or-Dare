package com.example.data.repository

interface FirebaseRepository {
    suspend fun createWaitingRoom(nickname: String)
}