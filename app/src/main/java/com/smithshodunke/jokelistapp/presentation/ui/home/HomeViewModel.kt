package com.smithshodunke.jokelistapp.presentation.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.domain.repository.JokeRepository
import com.smithshodunke.jokelistapp.presentation.util.BaseViewModel
import com.smithshodunke.jokelistapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: JokeRepository
) : BaseViewModel<HomeStateEvent, HomeViewState>(
    initialState = HomeViewState()
) {

    override fun handleStateEvent(stateEvent: HomeStateEvent) {
        when (stateEvent) {
            HomeStateEvent.NavigateToJokeList -> {}
            HomeStateEvent.GetNewJoke -> {
                viewModelScope.launch {
                    getNewJoke()
                }
            }
            HomeStateEvent.DismissDialog -> {
                setViewState {
                    copy(isDialogShown = false)
                }
            }
        }
    }

    private suspend fun getNewJoke() {
        repository.getRandomJoke(
            listOfFlags = listOf(Flags.EXPLICIT, Flags.NSFW, Flags.RACIST, Flags.SEXIST, Flags.RELIGIOUS)
        ).collect { resource ->
            when (resource) {
                is Resource.Error -> {
                    setViewState { HomeViewState(error = resource.message) }
                }
                is Resource.Loading -> {
                    setViewState { copy(isLoading = true, error = null) }
                }
                is Resource.Success -> {
                    Log.d(TAG, "response: ${resource.data}")

                    setViewState {
                        copy(
                            isDialogShown = true,
                            isLoading = false,
                            joke = resource.data
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}