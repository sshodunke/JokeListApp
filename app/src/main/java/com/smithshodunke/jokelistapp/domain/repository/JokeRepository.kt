package com.smithshodunke.jokelistapp.domain.repository

import com.smithshodunke.jokelistapp.data.remote.dto.JokeDto
import com.smithshodunke.jokelistapp.data.remote.dto.JokeListItemDto

interface JokeRepository {

    /**
     * Returns a random joke
     */
    suspend fun getRandomJoke(
        flags: String = ""
    ): JokeDto

    /**
     * Returns a list of jokes
     */
    suspend fun getListOfJokes(
        flags: String,
        amount: Int = 20
    ): JokeListItemDto
}