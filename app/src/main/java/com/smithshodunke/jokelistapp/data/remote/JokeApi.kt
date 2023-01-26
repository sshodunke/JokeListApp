package com.smithshodunke.jokelistapp.data.remote

import com.smithshodunke.jokelistapp.data.remote.dto.JokeDto
import com.smithshodunke.jokelistapp.data.remote.dto.JokeListItemDto
import com.smithshodunke.jokelistapp.util.Constants.ANY_JOKE_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {

    @GET(ANY_JOKE_ENDPOINT)
    suspend fun getRandomJoke(
        @Query("blacklistFlags") flags: String? = null,
    ): JokeDto

    @GET(ANY_JOKE_ENDPOINT)
    suspend fun getListOfJokes(
        @Query("blacklistFlags") flags: String? = null,
        @Query("amount") amount: Int
    ): JokeListItemDto
}