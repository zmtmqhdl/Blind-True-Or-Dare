package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.room.RoomDatabase
import com.example.data.room.RoomDao
import com.example.domain.repository.RoomRepository
import com.example.data.repositoryImpl.RoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDatabase::class.java,
            "example_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExampleDao(database: RoomDatabase): RoomDao {
        return database.userDao()
    }


}