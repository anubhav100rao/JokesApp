package com.example.jokesapp.di

import com.example.jokesapp.data.JokeApi
import com.example.jokesapp.data.JokeRepositoryImpl
import com.example.jokesapp.domain.GetJoke
import com.example.jokesapp.domain.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JokeModule {

    @Provides
    @Singleton
    fun provideJokeApi(): JokeApi {
        return Retrofit.Builder()
            .baseUrl(JokeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJokeRepository(
        api: JokeApi
    ): JokeRepository {
        return JokeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetJokeUseCase(
        repository: JokeRepository
    ): GetJoke {
        return GetJoke(repository)
    }

}