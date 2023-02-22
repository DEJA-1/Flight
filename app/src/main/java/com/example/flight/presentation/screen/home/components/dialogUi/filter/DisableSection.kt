package com.example.flight.presentation.screen.home.components.dialogUi.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.presentation.screen.home.components.CustomSwitch
import com.example.flight.presentation.viewModel.HomeViewModel

@Composable
fun DisableSection(
    viewModel: HomeViewModel
) {

    val isChecked = remember {
        mutableStateOf(viewModel.filterParams.value.disableNextDayArrivals.value)
    }

    Row(horizontalArrangement = Arrangement.SpaceBetween) {

        Box(
            modifier = Modifier.fillMaxWidth(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Disable next day arrivals",
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }

        CustomSwitch(isChecked = isChecked) {
            isChecked.value = !isChecked.value
            viewModel.updateDisableNextDayArrivalsFilter(isChecked.value)
        }

    }

}