package com.example.flight.presentation.screen.info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flight.R
import com.example.flight.ui.theme.spacing

@Preview
@Composable
fun FlightDrawing() {

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(3.dp)
                .background(MaterialTheme.colors.secondary)
        ) {

        }

        Icon(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.extraSmall,
                    end = MaterialTheme.spacing.extraSmall
                )
                .size(26.dp)
                .rotate(90f),
            painter = painterResource(id = R.drawable.airplane),
            contentDescription = "Airplane image",
            tint = MaterialTheme.colors.secondary
        )

    }

}