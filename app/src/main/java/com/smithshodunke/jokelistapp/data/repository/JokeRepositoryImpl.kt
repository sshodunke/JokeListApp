package com.smithshodunke.jokelistapp.data.repository

import com.smithshodunke.jokelistapp.data.mappers.toJoke
import com.smithshodunke.jokelistapp.data.mappers.toJokeList
import com.smithshodunke.jokelistapp.data.remote.JokeApi
import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.domain.repository.JokeRepository
import com.smithshodunke.jokelistapp.util.DefaultDispatcherProvider
import com.smithshodunke.jokelistapp.util.DispatcherProvider
import com.smithshodunke.jokelistapp.util.Resource
import com.smithshodunke.jokelistapp.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val jokeApi: JokeApi,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) : JokeRepository {

    override suspend fun getRandomJoke(listOfFlags: List<Flags>): Flow<Resource<Joke>> = flow {
        emit(Resource.Loading())

        val setOfFlags = mutableSetOf<String>().apply {
            listOfFlags.forEach { flag ->
                this.add(flag.flag)
            }
        }

        val flags = setOfFlags.toString()
            .replace(" ", "")
            .replace("[", "")
            .replace("]", "")

        emit(safeApiCall(dispatcher.io()) {
            jokeApi.getRandomJoke(
                flags = flags
            ).toJoke()
        })
    }

    override suspend fun getListOfJokes(
        listOfFlags: List<Flags>,
        amount: Int
    ): Flow<Resource<List<Joke>>> = flow {
        emit(Resource.Loading())

        val setOfFlags = mutableSetOf<String>().apply {
            listOfFlags.forEach { flag ->
                this.add(flag.flag)
            }
        }

        val flags = setOfFlags.toString()
            .replace(" ", "")
            .replace("[", "")
            .replace("]", "")

        emit(safeApiCall(dispatcher.io()) {
            jokeApi.getListOfJokes(
                flags = flags,
                amount = amount
            ).toJokeList()
        })
    }
}