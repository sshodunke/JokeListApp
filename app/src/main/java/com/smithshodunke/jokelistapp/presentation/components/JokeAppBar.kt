package com.smithshodunke.jokelistapp.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun JokeAppBar(
    onSettingsClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Jokes") },
        actions = {
            IconButton(onClick = { onSettingsClick() }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Setting Icon",
                    tint = Color.White
                )
            }
        }
    )
}