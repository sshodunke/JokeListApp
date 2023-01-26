package com.smithshodunke.jokelistapp.presentation.ui.jokelist

sealed class JokeListStateEvent {
    object RefreshList: JokeListStateEvent()
    object FetchMoreJokes: JokeListStateEvent()
}