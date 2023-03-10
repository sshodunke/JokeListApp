package com.smithshodunke.jokelistapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smithshodunke.jokelistapp.domain.model.joke.Joke
import com.smithshodunke.jokelistapp.presentation.theme.MediumDimension
import com.smithshodunke.jokelistapp.presentation.theme.SmallDimension

@Composable
fun JokeListItem(
    joke: Joke
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MediumDimension),
        verticalArrangement = Arrangement.Center
    ) {
        when (joke) {
            is Joke.SinglePartJoke -> {
                Text(joke.joke)
            }
            is Joke.TwoPartJoke -> {
                Column {
                    Text(joke.setup)
                    Spacer(modifier = Modifier.height(SmallDimension))
                    Text(text = joke.delivery)
                }
            }
        }
    }
}