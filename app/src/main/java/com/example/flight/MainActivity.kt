package com.example.flight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.flight.navigation.NavGraph
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.FlightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel by viewModels<ThemeViewModel>()
            FlightTheme(themeViewModel.isDarkTheme.value) {
                NavGraph(themeViewModel)
            }
        }
    }
}
