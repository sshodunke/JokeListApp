package com.smithshodunke.jokelistapp.presentation.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    navigateToRandomJokeScreen: () -> Unit,
    navigateToListScreen: () -> Unit
) {
    Text(text = "Home Screen")
}