package com.smithshodunke.jokelistapp.presentation.ui.home

import com.smithshodunke.jokelistapp.domain.model.joke.Joke

data class HomeViewState(
    val isLoading: Boolean = false,
    val isDialogShown: Boolean = false,
    val joke: Joke? = null,
    val error: String? = null,
)