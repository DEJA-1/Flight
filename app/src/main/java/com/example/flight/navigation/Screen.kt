package com.example.flight.navigation

sealed class Screen (val route: String) {
    object Home: Screen(route = "home_screen")
    object Info: Screen(route = "info_screen")
    object Saved: Screen(route = "saved_screen")
}