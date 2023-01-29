package com.smithshodunke.jokelistapp.domain.usecase

import com.smithshodunke.jokelistapp.data.mappers.toJokeList
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

class GetJokesUseCase @Inject constructor(
    private val jokeRepository: JokeRepository,
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) {
    suspend operator fun invoke(listOfFlags: List<Flags>, amount: Int = 20): Flow<Resource<List<Joke>>> =
        flow {
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
                jokeRepository.getListOfJokes(
                    flags = flags,
                    amount = amount
                ).toJokeList()
            })
        }
}