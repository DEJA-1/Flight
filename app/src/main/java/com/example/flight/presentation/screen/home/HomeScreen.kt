package com.example.flight.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flight.common.AppColors
import com.example.flight.navigation.Screen
import com.example.flight.presentation.screen.home.components.Header
import com.example.flight.presentation.screen.home.components.TopSection
import com.example.flight.presentation.viewmodel.HomeViewModel
import com.example.flight.presentation.viewmodel.ThemeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel = viewModel(),
    viewModel: HomeViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopSection(themeViewModel = themeViewModel, viewModel = viewModel)
        }

    }

}