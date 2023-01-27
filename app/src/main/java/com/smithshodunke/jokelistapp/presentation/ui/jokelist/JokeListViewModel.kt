package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import androidx.lifecycle.viewModelScope
import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.domain.repository.JokeRepository
import com.smithshodunke.jokelistapp.presentation.util.BaseViewModel
import com.smithshodunke.jokelistapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeListViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : BaseViewModel<JokeListStateEvent, JokeListViewState>(
    initialState = JokeListViewState()
) {

    private val currentListOfJokes = mutableListOf<Joke>()

    init {
        viewModelScope.launch {
            fetchJokes()
        }
    }

    override fun handleStateEvent(stateEvent: JokeListStateEvent) {
        when (stateEvent) {
            JokeListStateEvent.FetchMoreJokes -> {
                if (viewState.value.isLoading) return

                viewModelScope.launch {
                    fetchJokes()
                }
            }
        }
    }

    private suspend fun fetchJokes() {
        jokeRepository.getListOfJokes(
            listOfFlags = listOf(Flags.EXPLICIT, Flags.NSFW, Flags.RACIST, Flags.SEXIST, Flags.RELIGIOUS)
        ).collect { resource ->
            when (resource) {
                is Resource.Error -> {
                    setViewState {
                        JokeListViewState(error = resource.message)
                    }
                }
                is Resource.Loading -> {
                    setViewState {
                        copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    currentListOfJokes.addAll(resource.data ?: listOf())

                    setViewState {
                        copy(
                            isLoading = false,
                            jokesList = currentListOfJokes
                        )
                    }
                }
            }
        }
    }
}