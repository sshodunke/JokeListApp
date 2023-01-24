package com.smithshodunke.jokelistapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.smithshodunke.jokelistapp.presentation.ui.home.HomeScreen
import com.smithshodunke.jokelistapp.presentation.ui.jokelist.JokeListScreen
import com.smithshodunke.jokelistapp.presentation.ui.randomjoke.RandomJokeScreen


@Composable
fun JokeListAppGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = JokeListDestinations.JOKE_HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = JokeListDestinations.JOKE_HOME_ROUTE
        ) {
            HomeScreen(
                navigateToRandomJokeScreen = {
                    JokeListNavigationActions(navController).navigateToRandomJokeScreen
                },
                navigateToListScreen = {
                    JokeListNavigationActions(navController).navigateToJokeListScreen
                }
            )
        }
        composable(
            route = JokeListDestinations.JOKE_LIST_ROUTE
        ) {
            JokeListScreen()
        }

        composable(
            route = JokeListDestinations.RANDOM_JOKE_ROUTE
        ) {
            RandomJokeScreen()
        }
    }
}