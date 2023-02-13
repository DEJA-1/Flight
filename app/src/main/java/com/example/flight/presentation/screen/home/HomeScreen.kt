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
import androidx.navigation.NavController
import com.example.flight.common.AppColors
import com.example.flight.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.mBackground)
    ) {
        Column() {
            Text(
                text = "Home Screen",
                style = MaterialTheme.typography.h1,
                color = AppColors.mTextWhite
            )

            Button(
                onClick = { navController.navigate(Screen.Info.route) }) {
                Text(text = "Click")
            }
        }

    }

}