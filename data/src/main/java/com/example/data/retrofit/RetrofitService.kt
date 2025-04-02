package com.example.data.retrofit

import com.example.data.model.RetrofitData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("todos/{id}")
    suspend fun get(@Path("id") postId: Int): RetrofitData

    @GET("todos")
    suspend fun getQuery(
        @Query("userId") userId: Int,
        @Query("completed") completed: Boolean
    ): List<RetrofitData>

    @POST("todos")
    suspend fun post(@Body newExample: RetrofitData): RetrofitData

    @PUT("todos/{id}")
    suspend fun put(@Path("id") postId: Int, @Body updatedExample: RetrofitData): RetrofitData

    @PATCH("todos/{id}")
    suspend fun patch(@Path("id") postId: Int, @Body partialUpdate: Map<String, Any>): RetrofitData

    @DELETE("todos/{id}")
    suspend fun delete(@Path("id") postId: Int): Response<Unit>
}