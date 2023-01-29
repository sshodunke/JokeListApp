package com.smithshodunke.jokelistapp.usecase

import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.domain.usecase.GetJokesUseCase
import com.smithshodunke.jokelistapp.util.DefaultDispatcherProvider
import com.smithshodunke.jokelistapp.util.JokeRepositoryFake
import com.smithshodunke.jokelistapp.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetJokesUseCaseTest {

    private lateinit var getJokesUseCase: GetJokesUseCase

    @Before
    fun setup() {
        getJokesUseCase = GetJokesUseCase(
            jokeRepository = JokeRepositoryFake(),
            dispatcher = DefaultDispatcherProvider()
        )
    }

    @Test
    fun `Get Loading state upon executing usecase`() = runBlocking {
        val listResults = collectResults(useCase = getJokesUseCase, flags = listOf(), amount = 20)
        assert(listResults.first() is Resource.Loading)
    }

    @Test
    fun `Usecase executes with no errors`() = runBlocking {
        val listResults = collectResults(useCase = getJokesUseCase, flags = listOf(), amount = 20)
        assert((listResults.count { resource -> resource.message !== null } == 0) && listResults.last() is Resource.Success)
    }

    private suspend fun collectResults(
        useCase: GetJokesUseCase,
        flags: List<Flags>,
        amount: Int
    ): List<Resource<List<Joke>>> {
        val listResults = ArrayList<Resource<List<Joke>>>()
        useCase(flags, amount).collect { value -> listResults += value }
        return listResults
    }
}