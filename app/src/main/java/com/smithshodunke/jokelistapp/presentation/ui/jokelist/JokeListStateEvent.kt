package com.smithshodunke.jokelistapp.presentation.ui.jokelist

sealed class JokeListStateEvent {
    object FetchMoreJokes: JokeListStateEvent()
}