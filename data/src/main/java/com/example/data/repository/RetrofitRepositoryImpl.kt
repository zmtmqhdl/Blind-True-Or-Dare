package com.example.data.repository

import com.example.data.model.RetrofitData
import com.example.data.retrofit.RetrofitService
import retrofit2.Response

class RetrofitRepositoryImpl(private val retrofitService: RetrofitService) : RetrofitRepository {
    override suspend fun get(postId: Int): RetrofitData {
        return retrofitService.get(postId)
    }

    override suspend fun getQuery(userId: Int, completed: Boolean): List<RetrofitData> {
        return retrofitService.getQuery(userId, completed)
    }

    override suspend fun post(newExample: RetrofitData): RetrofitData {
        return retrofitService.post(newExample)
    }

    override suspend fun put(postId: Int, updatedExample: RetrofitData): RetrofitData {
        return retrofitService.put(postId, updatedExample)
    }

    override suspend fun patch(postId: Int, partialUpdate: Map<String, Any>): RetrofitData {
        return retrofitService.patch(postId, partialUpdate)
    }

    override suspend fun delete(postId: Int): Response<Unit> {
        return retrofitService.delete(postId)
    }
}