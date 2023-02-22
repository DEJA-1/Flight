package com.example.flight.presentation.screen.home.components.dialogUi.passenger

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.flight.ui.theme.spacing


@Composable
fun PassengerButton(
    icon: Painter,
    onPassengerButtonClicked: () -> Unit
) {
    Card(
        shape = CircleShape,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onPassengerButtonClicked()
            }
            .padding(MaterialTheme.spacing.extraSmall)
    ) {
        Icon(
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
            painter = icon,
            contentDescription = "+/- icon",
            tint = Color.Black
        )
    }
}