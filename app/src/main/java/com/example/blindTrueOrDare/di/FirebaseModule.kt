package com.example.blindTrueOrDare.di

import com.example.data.repositoryImpl.FirebaseRepository
import com.example.data.repositoryImpl.FirebaseRepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseDatabase(): FirebaseDatabase {
            return FirebaseDatabase.getInstance()
        }
    }

    @Binds
    @Singleton
    abstract fun bindFirebaseRepository(
        impl: FirebaseRepositoryImpl
    ): FirebaseRepository
}