package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import android.util.Log
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
    initialState = JokeListViewState(
        selectedFlagsList = setOf(
            Flags.EXPLICIT,
            Flags.NSFW,
            Flags.RACIST,
            Flags.RELIGIOUS,
            Flags.SEXIST
        )
    )
) {
    private var currentListOfJokes = mutableListOf<Joke>()

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
            JokeListStateEvent.OpenSettings -> {
                setViewState { copy(isDialogShown = true) }
            }
            JokeListStateEvent.DismissSettings -> {
                setViewState { copy(isDialogShown = false) }
            }
            is JokeListStateEvent.SelectedTickbox -> {
                val selectedFlag = stateEvent.flag
                val list: MutableSet<Flags> = viewState.value.selectedFlagsList.toMutableSet()
                Log.d("TAG", "handleStateEvent: $list")

                if (selectedFlag in viewState.value.selectedFlagsList) {
                    list.remove(selectedFlag)
                    setViewState { copy(selectedFlagsList = list) }
                } else {
                    list.add(selectedFlag)
                    setViewState { copy(selectedFlagsList = list) }
                }
            }
            is JokeListStateEvent.RefreshList -> {
                if (viewState.value.isLoading) return

                viewModelScope.launch {
                    fetchJokes(refreshList = true)
                }
            }
        }
    }

    private suspend fun fetchJokes(refreshList: Boolean = false) {
        jokeRepository.getListOfJokes(
            listOfFlags = viewState.value.selectedFlagsList.toList()
        ).collect { resource ->
            when (resource) {
                is Resource.Error -> {
                    setViewState {
                        copy(
                            isLoading = false,
                            error = resource.message
                        )
                    }
                }
                is Resource.Loading -> {
                    setViewState {
                        copy(
                            error = null,
                            isLoading = true
                        )
                    }
                }
                is Resource.Success -> {
                    if (refreshList) {
                        currentListOfJokes = resource.data?.toMutableList() ?: mutableListOf()
                    } else {
                        currentListOfJokes.addAll(resource.data ?: listOf())
                    }

                    setViewState {
                        copy(
                            isDialogShown = false,
                            isLoading = false,
                            jokesList = currentListOfJokes
                        )
                    }
                }
            }
        }
    }
}