package com.smithshodunke.jokelistapp.presentation.ui

import androidx.compose.runtime.Composable
import com.smithshodunke.jokelistapp.presentation.navigation.JokeListAppGraph
import com.smithshodunke.jokelistapp.presentation.theme.JokeListAppTheme

@Composable
fun JokeListApp() {
    JokeListAppTheme {
        JokeListAppGraph()
    }
}