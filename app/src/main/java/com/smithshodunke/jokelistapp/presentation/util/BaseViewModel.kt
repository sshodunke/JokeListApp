package com.smithshodunke.jokelistapp.presentation.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<StateEvent, ViewState>(
    val initialState: ViewState
) : ViewModel() {

    private val _viewState = mutableStateOf(initialState)
    val viewState: State<ViewState>
        get() = _viewState

    private val stateEventChannel = Channel<StateEvent>(Channel.UNLIMITED)

    protected fun setViewState(reduce: ViewState.() -> ViewState) {
        val newViewState = viewState.value.reduce()
        _viewState.value = newViewState
    }

    fun setStateEvent(stateEvent: StateEvent) {
        viewModelScope.launch {
            stateEventChannel.send(stateEvent)
        }
    }

    init {
        viewModelScope.launch {
            stateEventChannel.consumeAsFlow().collect { stateEvent ->
                handleStateEvent(stateEvent)
            }
        }
    }

    abstract fun handleStateEvent(stateEvent: StateEvent)
}