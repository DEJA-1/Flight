package com.example.flight.presentation.screen.common_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flight.ui.theme.spacing


@Composable
fun MyDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        thickness = 2.dp,
        color = MaterialTheme.colors.secondary,
        modifier = modifier.padding(
            top = MaterialTheme.spacing.small,
            bottom = MaterialTheme.spacing.small
        )
    )
}