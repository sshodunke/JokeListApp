package com.smithshodunke.jokelistapp.data.repository

import com.smithshodunke.jokelistapp.data.remote.JokeApi
import com.smithshodunke.jokelistapp.data.remote.dto.JokeDto
import com.smithshodunke.jokelistapp.data.remote.dto.JokeListItemDto
import com.smithshodunke.jokelistapp.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val jokeApi: JokeApi,
) : JokeRepository {

    override suspend fun getRandomJoke(flags: String): JokeDto {
        return jokeApi.getRandomJoke(flags = flags)
    }

    override suspend fun getListOfJokes(flags: String, amount: Int): JokeListItemDto {
        return jokeApi.getListOfJokes(
            flags = flags,
            amount = amount
        )
    }
}