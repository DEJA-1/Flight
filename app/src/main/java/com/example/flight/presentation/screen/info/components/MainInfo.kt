package com.example.flight.presentation.screen.info.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MainInfo(
    place: String,
    time: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = place,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 28.sp
        )

        Text(
            text = time,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )

    }
}