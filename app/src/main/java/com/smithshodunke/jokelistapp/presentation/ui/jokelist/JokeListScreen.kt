package com.smithshodunke.jokelistapp.presentation.ui.jokelist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.smithshodunke.jokelistapp.domain.model.flags.Flags
import com.smithshodunke.jokelistapp.presentation.components.JokeAppBar
import com.smithshodunke.jokelistapp.presentation.components.JokeListItem
import com.smithshodunke.jokelistapp.presentation.components.LoadingState
import com.smithshodunke.jokelistapp.presentation.util.isScrolledToEnd
import java.util.*

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JokeListScreen(
    viewState: JokeListViewState,
    setStateEvent: (JokeListStateEvent) -> Unit
) {
    Scaffold(
        topBar = {
            JokeAppBar {
                setStateEvent(JokeListStateEvent.OpenSettings)
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val scrollState = rememberLazyListState()

            if (viewState.isLoading) {
                LoadingState()
            }

            if (viewState.isDialogShown) {
                AlertDialog(
                    title = { Text(text = "Joke Filters") },
                    onDismissRequest = { setStateEvent(JokeListStateEvent.DismissSettings) },
                    confirmButton = {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            TextButton(onClick = { setStateEvent(JokeListStateEvent.RefreshList) }) {
                                Text(text = "Get New Jokes!")
                            }
                        }
                    },
                    text = {
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Flags.values().forEach { flag ->
                                val checked = flag in viewState.selectedFlagsList

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(
                                        checked = checked,
                                        onCheckedChange = {
                                            setStateEvent(JokeListStateEvent.SelectedTickbox(flag))
                                        },
                                    )
                                    Text(text = flag.flag.uppercase(Locale.ROOT))
                                }
                            }
                        }
                    }
                )
            }

            if (viewState.error.isNullOrBlank()) {
                if (viewState.jokesList.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "List is empty")
                        Button(onClick = { setStateEvent(JokeListStateEvent.RefreshList) }) {
                            Text(text = "Retry")
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = scrollState
                    ) {
                        items(viewState.jokesList) { joke ->
                            JokeListItem(joke)
                            Divider()
                        }
                    }

                    val endOfListReached: Boolean by remember {
                        derivedStateOf { scrollState.isScrolledToEnd() }
                    }

                    LaunchedEffect(endOfListReached) {
                        setStateEvent(JokeListStateEvent.FetchMoreJokes)
                    }
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
}