package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smithshodunke.jokelistapp.presentation.components.JokeListItem

@Composable
fun JokeListScreen(
    viewModel: JokeListViewModel
) {
    val viewState = viewModel.viewState.value

    JokeListScreen(
        viewState = viewState
    )
}

@Composable
fun JokeListScreen(
    viewState: JokeListViewState
) {

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn() {
            items(viewState.jokesList) { joke ->
                JokeListItem(joke)
                Divider()
            }
        }
    }
}