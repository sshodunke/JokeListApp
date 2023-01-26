package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.smithshodunke.jokelistapp.presentation.components.JokeListItem
import com.smithshodunke.jokelistapp.presentation.components.LoadingState
import com.smithshodunke.jokelistapp.presentation.util.isScrolledToEnd

@Composable
fun JokeListScreen(
    viewModel: JokeListViewModel
) {
    val viewState = viewModel.viewState.value

    JokeListScreen(
        viewState = viewState,
        setStateEvent = { stateEvent ->
            viewModel.setStateEvent(stateEvent)
        }
    )
}

@Composable
fun JokeListScreen(
    viewState: JokeListViewState,
    setStateEvent: (JokeListStateEvent) -> Unit
) {
    val scrollState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {

        if (viewState.isLoading) {
            LoadingState()
        }

        LazyColumn(
            state = scrollState,

        ) {
            items(viewState.jokesList) { joke ->
                JokeListItem(joke)
                Divider()
            }
        }

        val endOfListReached: Boolean by remember {
            derivedStateOf {
                scrollState.isScrolledToEnd()
            }
        }

        LaunchedEffect(endOfListReached) {
            setStateEvent(JokeListStateEvent.FetchMoreJokes)
        }
    }
}