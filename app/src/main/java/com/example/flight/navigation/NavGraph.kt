package com.example.flight.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flight.presentation.screen.home.HomeScreen
import com.example.flight.presentation.screen.info.InfoScreen
import com.example.flight.presentation.screen.saved.SavedScreen
import com.example.flight.presentation.viewmodel.HomeViewModel
import com.example.flight.presentation.viewmodel.ThemeViewModel

@Composable
fun NavGraph(
    themeViewModel: ThemeViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {

            val viewModel = viewModel<HomeViewModel>()
            HomeScreen(navController = navController, themeViewModel = themeViewModel, viewModel = viewModel)
        }

        composable(
            route = Screen.Info.route
        ) {
            InfoScreen(navController = navController)
        }

        composable(
            route = Screen.Saved.route
        ) {
            SavedScreen(navController = navController)
        }
    }
}