package com.smithshodunke.jokelistapp.presentation.navigation

import androidx.navigation.NavController
import com.smithshodunke.jokelistapp.presentation.navigation.JokeListDestinations.JOKE_LIST_ROUTE

object JokeListDestinations {
    const val JOKE_HOME_ROUTE = "route_joke_home"
    const val JOKE_LIST_ROUTE = "route_joke_list"
}

class JokeListNavigationActions(private val navController: NavController) {

    val navigateToJokeListScreen: () -> Unit = {
        navController.navigate(JOKE_LIST_ROUTE)
    }
}