package com.example.blindTrueOrDare.di

import com.example.data.repositoryImpl.LoadingRepositoryImpl
import com.example.domain.repository.LoadingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    @Singleton
    abstract fun bindLoadingRepository(impl: LoadingRepositoryImpl): LoadingRepository

}