package com.smithshodunke.jokelistapp.util

import com.smithshodunke.jokelistapp.data.remote.dto.Flags
import com.smithshodunke.jokelistapp.data.remote.dto.JokeDto
import com.smithshodunke.jokelistapp.data.remote.dto.JokeListItemDto
import com.smithshodunke.jokelistapp.domain.repository.JokeRepository

class JokeRepositoryFake : JokeRepository {

    var jokeDtoResponse: JokeDto = JokeDto(
        category = "",
        joke = "",
        delivery = "",
        error = false,
        flags = Flags(
            explicit = false,
            nsfw = false,
            political = false,
            racist = false,
            religious = false,
            sexist = false
        ),
        id = 1,
        lang = "eng",
        safe = true,
        setup = "",
        type = ""
    )

    var jokeListItemDtoResponse: JokeListItemDto = JokeListItemDto(
        amount = 5,
        error = false,
        jokes = listOf(
            jokeDtoResponse,
            jokeDtoResponse,
            jokeDtoResponse,
            jokeDtoResponse,
            jokeDtoResponse,
        )
    )

    override suspend fun getRandomJoke(flags: String): JokeDto {
        return jokeDtoResponse
    }

    override suspend fun getListOfJokes(flags: String, amount: Int): JokeListItemDto {
        return jokeListItemDtoResponse
    }

}