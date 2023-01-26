package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import com.smithshodunke.jokelistapp.domain.model.joke.Joke

data class JokeListViewState(
    val isLoading: Boolean = false,
    val isDialogShown: Boolean = false,
    val jokesList: List<Joke> = listOf(),
    val error: String? = null,
)
