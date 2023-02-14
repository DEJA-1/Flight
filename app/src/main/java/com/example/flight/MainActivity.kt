package com.example.flight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flight.navigation.NavGraph
import com.example.flight.presentation.viewmodel.ThemeViewModel
import com.example.flight.ui.theme.FlightTheme

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
