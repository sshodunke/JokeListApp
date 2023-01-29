package com.smithshodunke.jokelistapp

import com.smithshodunke.jokelistapp.usecase.GetJokesUseCaseTest
import com.smithshodunke.jokelistapp.usecase.GetRandomJokeUseCaseTest
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)

@Suite.SuiteClasses(
    GetRandomJokeUseCaseTest::class,
    GetJokesUseCaseTest::class
)
class JokeListAppSuite