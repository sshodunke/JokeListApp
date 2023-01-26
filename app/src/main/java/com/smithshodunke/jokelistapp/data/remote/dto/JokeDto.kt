package com.smithshodunke.jokelistapp.data.remote.dto

data class JokeDto(
    val category: String,
    val joke: String?,
    val delivery: String,
    val error: Boolean? = null,
    val flags: Flags,
    val id: Int,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)