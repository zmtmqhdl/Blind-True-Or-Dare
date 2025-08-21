package com.example.data.di

import com.example.data.repositoryImpl.LoadingRepositoryImpl
import com.example.data.repositoryImpl.RoomRepositoryImpl
import com.example.data.repositoryImpl.WebSocketRepositoryImpl
import com.example.domain.repository.LoadingRepository
import com.example.domain.repository.RoomRepository
import com.example.domain.repository.WebSocketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoadingRepository(impl: LoadingRepositoryImpl): LoadingRepository

    @Binds
    @Singleton
    abstract fun bindWebSocketRepository(impl: WebSocketRepositoryImpl): WebSocketRepository

    @Binds
    @Singleton
    abstract fun bindRoomRepository(impl: RoomRepositoryImpl): RoomRepository

}