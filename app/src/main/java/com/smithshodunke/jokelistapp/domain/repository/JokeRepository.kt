package com.smithshodunke.jokelistapp.domain.repository

import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface JokeRepository {

    /**
     * Returns a random joke
     */
    suspend fun getRandomJoke(
        listOfFlags: List<Flags> = listOf()
    ): Flow<Resource<Joke>>

    /**
     * Returns a list of jokes
     */
    suspend fun getListOfJokes(
        listOfFlags: List<Flags> = listOf(),
        amount: Int = 20
    ): Flow<Resource<List<Joke>>>
}