package com.smithshodunke.jokelistapp.presentation.ui.home

sealed class HomeStateEvent {
    object NavigateToJokeList : HomeStateEvent()
    object GetNewJoke : HomeStateEvent()
    object DismissDialog : HomeStateEvent()
}
