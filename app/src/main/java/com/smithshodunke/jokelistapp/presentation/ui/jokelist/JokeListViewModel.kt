package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import androidx.lifecycle.viewModelScope
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
            JokeListStateEvent.FetchMoreJokes -> {}
            JokeListStateEvent.RefreshList -> {}
        }
    }

    private suspend fun fetchJokes() {
        jokeRepository.getListOfJokes().collect { resource ->
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
                    setViewState {
                        copy(
                            isLoading = false,
                            jokesList = resource.data ?: listOf()
                        )
                    }

                    currentListOfJokes.addAll(resource.data ?: listOf())
                }
            }
        }
    }
}