package com.smithshodunke.jokelistapp.domain.model.joke

import com.smithshodunke.jokelistapp.data.remote.dto.Flags

sealed class Joke {
    data class SinglePartJoke(
        val category: String,
        val joke: String,
        val flags: Flags,
        val id: Int,
        val safe: Boolean,
        val type: String
    ) : Joke()

    data class TwoPartJoke(
        val category: String,
        val delivery: String,
        val setup: String,
        val flags: Flags,
        val id: Int,
        val safe: Boolean,
        val type: String
    ): Joke()
}