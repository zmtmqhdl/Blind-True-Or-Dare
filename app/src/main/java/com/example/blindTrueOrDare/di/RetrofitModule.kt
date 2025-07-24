package com.example.blindTrueOrDare.di

import com.example.data.api.BlindTrueOrDareApi
import com.example.data.client.RetrofitClient
import com.example.data.repositoryImpl.RoomRepositoryImpl
import com.example.domain.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): BlindTrueOrDareApi {
        return RetrofitClient.blindTrueOrDareApi
    }

    @Provides
    @Singleton
    fun provideRetrofitRepository(blindTrueOrDareApi: BlindTrueOrDareApi): RoomRepository {
        return RoomRepositoryImpl(blindTrueOrDareApi)
    }
}