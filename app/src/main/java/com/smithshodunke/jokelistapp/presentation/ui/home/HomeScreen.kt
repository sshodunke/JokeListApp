package com.smithshodunke.jokelistapp.presentation.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.presentation.components.LoadingState
import com.smithshodunke.jokelistapp.presentation.theme.SmallDimension
import com.smithshodunke.jokelistapp.util.Constants.ERROR_RETRIEVING_JOKE

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToListScreen: () -> Unit
) {
    val viewState = viewModel.viewState.value
    val context = LocalContext.current

    HomeScreen(
        viewState = viewState,
        navigateToListScreen = navigateToListScreen,
        setStateEvent = { stateEvent ->
            viewModel.handleStateEvent(stateEvent)
        }
    )


    if (viewModel.showToast.value) {
        Toast.makeText(context, viewState.error, Toast.LENGTH_SHORT).show()
        viewModel.toggleToast(show = false)
    }

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
        if (viewState.isLoading) {
            LoadingState()
        }

        Button(onClick = { setStateEvent(HomeStateEvent.GetNewJoke) }) {
            Text(text = "Random Joke!")
        }

        Button(onClick = navigateToListScreen) {
            Text(text = "List of Jokes!")
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
                            when (joke) {
                                is Joke.SinglePartJoke -> {
                                    Text(text = joke.joke, textAlign = TextAlign.Center)
                                }
                                is Joke.TwoPartJoke -> {
                                    Text(text = joke.setup, textAlign = TextAlign.Center)
                                    Spacer(modifier = Modifier.height(SmallDimension))
                                    Text(text = joke.delivery, textAlign = TextAlign.Center)
                                }
                            }
                        } ?: run {
                            Text(text = ERROR_RETRIEVING_JOKE, textAlign = TextAlign.Center)
                        }
                    }
                }
            )
        }
    }
}