package com.example.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BasicRetrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @BasicRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val BASE_URL = "http://10.0.2.2:8080/"

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            // BODY 전체 다 보기
            // HEADERS 헤더만 보기
            // BASIC 기본 정보만 보기
            // NONE 로그 끄기
        }

        val json = Json {
            ignoreUnknownKeys = true
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(
                timeout = 10,
                unit = TimeUnit.SECONDS
            )
            .readTimeout(
                timeout = 10,
                unit = TimeUnit.SECONDS
            )
            .writeTimeout(
                timeout = 10,
                unit = TimeUnit.SECONDS
            )
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}