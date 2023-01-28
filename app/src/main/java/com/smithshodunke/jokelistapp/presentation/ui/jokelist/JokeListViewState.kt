package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.model.joke.Joke

data class JokeListViewState(
    val isLoading: Boolean = false,
    val jokesList: List<Joke> = listOf(),
    val isDialogShown: Boolean = false,
    val selectedFlagsList: Set<Flags> = setOf(),
    val error: String? = null
)
