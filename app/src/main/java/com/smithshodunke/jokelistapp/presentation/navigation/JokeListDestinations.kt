package com.smithshodunke.jokelistapp.presentation.navigation

import androidx.navigation.NavController
import com.smithshodunke.jokelistapp.presentation.navigation.JokeListDestinations.JOKE_HOME_ROUTE
import com.smithshodunke.jokelistapp.presentation.navigation.JokeListDestinations.JOKE_LIST_ROUTE
import com.smithshodunke.jokelistapp.presentation.navigation.JokeListDestinations.RANDOM_JOKE_ROUTE

object JokeListDestinations {
    const val JOKE_HOME_ROUTE = "route_joke_home"
    const val JOKE_LIST_ROUTE = "route_joke_list"
    const val RANDOM_JOKE_ROUTE = "route_random_joke"
}

class JokeListNavigationActions(private val navController: NavController) {

    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(JOKE_HOME_ROUTE) {

        }
    }

    val navigateToJokeListScreen: () -> Unit = {
        navController.navigate(JOKE_LIST_ROUTE) {

        }
    }

    val navigateToRandomJokeScreen: () -> Unit = {
        navController.navigate(RANDOM_JOKE_ROUTE)
    }
}