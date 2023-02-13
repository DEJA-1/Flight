package com.example.flight.presentation.screen.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.flight.common.AppColors

@Composable
fun InfoScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.mBackground)
    ) {
        Text(
            text = "Info Screen",
            style = MaterialTheme.typography.h1,
            color = AppColors.mTextWhite
        )
    }

}