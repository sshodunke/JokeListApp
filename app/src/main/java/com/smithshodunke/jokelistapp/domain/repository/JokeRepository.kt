package com.smithshodunke.jokelistapp.domain.repository

import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface JokeRepository {

    /**
     * Returns a random joke
     */
    suspend fun getRandomJoke(
        flags: List<Flags> = listOf()
    ): Flow<Resource<Joke>>

    /**
     * Returns a list of jokes
     */
    suspend fun getListOfJokes(
        flags: List<Flags> = listOf(),
        amount: Int = 20
    ): Flow<Resource<List<Joke>>>
}

enum class Flags(val flag: String) {
    NSFW("nsfw"),
    POLITICAL("political"),
    RELIGIOUS("religious"),
    RACIST("racist"),
    SEXIST("sexist"),
    EXPLICIT("explicit")
}