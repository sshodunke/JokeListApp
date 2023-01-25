package com.smithshodunke.jokelistapp.data.remote.dto

data class JokeListItemDto(
    val amount: Int,
    val error: Boolean,
    val jokes: List<JokeDto>
)