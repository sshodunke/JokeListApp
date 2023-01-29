package com.smithshodunke.jokelistapp.usecase

import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.domain.usecase.GetRandomJokeUseCase
import com.smithshodunke.jokelistapp.util.DefaultDispatcherProvider
import com.smithshodunke.jokelistapp.util.JokeRepositoryFake
import com.smithshodunke.jokelistapp.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetRandomJokeUseCaseTest {

    private lateinit var getRandomJokeUseCase: GetRandomJokeUseCase

    @Before
    fun setup() {
        getRandomJokeUseCase = GetRandomJokeUseCase(
            jokeRepository = JokeRepositoryFake(),
            dispatcher = DefaultDispatcherProvider()
        )
    }

    @Test
    fun `Get Loading state upon executing usecase`() = runBlocking {
        val listResults = collectResults(getRandomJokeUseCase, listOf())
        assert(listResults.first() is Resource.Loading)
    }

    @Test
    fun `Usecase executes with no errors`() = runBlocking {
        val listResults = collectResults(getRandomJokeUseCase, listOf())
        assert((listResults.count { resource -> resource.message !== null } == 0) && listResults.last() is Resource.Success)
    }

    private suspend fun collectResults(
        useCase: GetRandomJokeUseCase,
        flags: List<Flags>
    ): List<Resource<Joke>> {
        val listResults = ArrayList<Resource<Joke>>()
        useCase(flags).collect { value -> listResults += value }
        return listResults
    }
}