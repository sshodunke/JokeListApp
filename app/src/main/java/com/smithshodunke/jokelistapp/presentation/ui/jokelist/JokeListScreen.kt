package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        if (viewState.isLoading) {
            LoadingState()
        }

        if (viewState.error.isNullOrBlank()) {
            LazyColumn(
                state = scrollState
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
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = viewState.error,
                    color = Color.Red,
                )
                Button(onClick = { setStateEvent(JokeListStateEvent.FetchMoreJokes) }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}