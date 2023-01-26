package com.smithshodunke.jokelistapp.domain.model.joke

import com.smithshodunke.jokelistapp.data.remote.dto.Flags

data class Joke(
    val category: String,
    val delivery: String?,
    val joke: String?,
    val setup: String?,
    val flags: Flags,
    val id: Int,
    val safe: Boolean,
    val type: String
)