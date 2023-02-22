package com.example.flight.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flight.presentation.screen.home.HomeScreen
import com.example.flight.presentation.screen.info.InfoScreen
import com.example.flight.presentation.screen.saved.SavedScreen
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel

@Composable
fun NavGraph(
    themeViewModel: ThemeViewModel
) {
    val navController = rememberNavController()
    val commonViewModel = viewModel<CommonViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {

            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                navController = navController,
                themeViewModel = themeViewModel,
                viewModel = viewModel,
                commonViewModel = commonViewModel
            )
        }

        composable(
            route = Screen.Info.route
        ) {
            InfoScreen(
                navController = navController,
                themeViewModel = themeViewModel,
                commonViewModel = commonViewModel
            )
        }

        composable(
            route = Screen.Saved.route
        ) {
            SavedScreen(navController = navController)
        }
    }
}