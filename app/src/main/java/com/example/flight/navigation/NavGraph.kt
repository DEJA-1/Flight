package com.example.flight.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flight.presentation.screen.home.HomeScreen
import com.example.flight.presentation.screen.info.InfoScreen
import com.example.flight.presentation.screen.saved.SavedScreen
import com.example.flight.presentation.viewModel.*

@Composable
fun NavGraph(
    themeViewModel: ThemeViewModel,
    navController: NavHostController = rememberNavController(),
) {

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
                navigateToInfoScreen = { navController.navigate(Screen.Info.route) },
                navigateToSavedScreen = { navController.navigate(Screen.Saved.route) },
                themeViewModel = themeViewModel,
                viewModel = viewModel,
                commonViewModel = commonViewModel
            )
        }

        composable(
            route = Screen.Info.route
        ) {
            val viewModel = hiltViewModel<InfoViewModel>()
            InfoScreen(
                viewModel = viewModel,
                commonViewModel = commonViewModel
            )
        }

        composable(
            route = Screen.Saved.route
        ) {
            val viewModel = hiltViewModel<SavedViewModel>()
            SavedScreen(
                navigateToInfoScreen = { navController.navigate(Screen.Info.route) },
                viewModel = viewModel,
                commonViewModel = commonViewModel
            )
        }
    }
}