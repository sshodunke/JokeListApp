package com.smithshodunke.jokelistapp.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.smithshodunke.jokelistapp.presentation.theme.SmallDimension

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToListScreen: () -> Unit
) {
    val viewState = viewModel.viewState.value

    HomeScreen(
        viewState = viewState,
        navigateToListScreen = navigateToListScreen,
        setStateEvent = { stateEvent ->
            viewModel.handleStateEvent(stateEvent)
        }
    )
}

@Composable
fun HomeScreen(
    viewState: HomeViewState,
    setStateEvent: (HomeStateEvent) -> Unit,
    navigateToListScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { setStateEvent(HomeStateEvent.GetNewJoke) }) {
                Text(text = "Random Joke!")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = navigateToListScreen) {
                Text(text = "List of Jokes!")
            }
        }

        if (viewState.isDialogShown) {
            AlertDialog(
                onDismissRequest = { setStateEvent(HomeStateEvent.DismissDialog) },
                confirmButton = {
                    TextButton(
                        onClick = { setStateEvent(HomeStateEvent.DismissDialog) }
                    ) {
                        Text(text = "OK")
                    }
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        viewState.joke?.let { joke ->
                            if (!joke.setup.isNullOrEmpty() && !joke.delivery.isNullOrEmpty()) {
                                Text(text = joke.setup, textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.height(SmallDimension))
                                Text(text = joke.delivery, textAlign = TextAlign.Center)
                            } else if (!joke.joke.isNullOrEmpty()) {
                                Text(text = joke.joke, textAlign = TextAlign.Center)
                            } else {
                                Text(
                                    text = "Error in retrieving joke",
                                    textAlign = TextAlign.Center
                                )
                            }
                        } ?: run {
                            Text(text = "Error in retrieving joke", textAlign = TextAlign.Center)
                        }
                    }
                }
            )
        }
    }
}