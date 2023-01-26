package com.smithshodunke.jokelistapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smithshodunke.jokelistapp.presentation.ui.home.HomeScreen
import com.smithshodunke.jokelistapp.presentation.ui.home.HomeViewModel
import com.smithshodunke.jokelistapp.presentation.ui.jokelist.JokeListScreen
import com.smithshodunke.jokelistapp.presentation.ui.jokelist.JokeListViewModel

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
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                navigateToListScreen = {
                    JokeListNavigationActions(navController).navigateToJokeListScreen()
                }
            )
        }
        composable(
            route = JokeListDestinations.JOKE_LIST_ROUTE
        ) {
            val viewModel: JokeListViewModel = hiltViewModel()
            JokeListScreen(
                viewModel = viewModel
            )
        }
    }
}