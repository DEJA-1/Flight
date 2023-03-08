package com.example.flight.presentation.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DoneButton(
    modifier: Modifier = Modifier,
    loadingLocation: Boolean = false,
    isDoneEnabled: MutableState<Boolean>,
    onDoneQuitClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = (isDoneEnabled.value && !loadingLocation)
                ) {
                    onDoneQuitClick()
                }
                .background(
                    if (isDoneEnabled.value && !loadingLocation)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.secondary
                ),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                text = if (loadingLocation)
                    "Loading.."
                else
                    "Done",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )

        }
    }
}