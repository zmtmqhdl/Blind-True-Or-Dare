package com.example.data.di

import com.example.data.api.BlindTrueOrDareApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideBlindTrueOrDareApi(@BasicRetrofit retrofit: Retrofit): BlindTrueOrDareApi=
        provideApi(retrofit = retrofit)

}

inline fun <reified I> provideApi(retrofit: Retrofit): I = retrofit.create(I::class.java)