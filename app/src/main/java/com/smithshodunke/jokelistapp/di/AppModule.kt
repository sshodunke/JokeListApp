package com.smithshodunke.jokelistapp.di

import com.smithshodunke.jokelistapp.data.remote.JokeApi
import com.smithshodunke.jokelistapp.data.repository.JokeRepositoryImpl
import com.smithshodunke.jokelistapp.domain.repository.JokeRepository
import com.smithshodunke.jokelistapp.util.DefaultDispatcherProvider
import com.smithshodunke.jokelistapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideJokeRepository(
        jokeApi: JokeApi
    ): JokeRepository {
        return JokeRepositoryImpl(
            jokeApi = jokeApi
        )
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}