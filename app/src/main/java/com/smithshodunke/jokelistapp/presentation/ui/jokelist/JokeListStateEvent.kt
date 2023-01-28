package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import com.smithshodunke.jokelistapp.domain.model.flags.Flags

sealed class JokeListStateEvent {
    object FetchMoreJokes: JokeListStateEvent()
    object OpenSettings: JokeListStateEvent()
    object DismissSettings: JokeListStateEvent()
    class SelectedTickbox(val flag: Flags): JokeListStateEvent()
    object RefreshList: JokeListStateEvent()
}