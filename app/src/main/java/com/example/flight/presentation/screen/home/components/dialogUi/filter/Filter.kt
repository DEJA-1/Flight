package com.example.flight.presentation.screen.home.components.dialogUi.filter

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun DialogUiFilter(
    isDoneEnabled: MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    isDoneEnabled.value = true

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.80f)
            .padding(MaterialTheme.spacing.small),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            SliderSection(viewModel = viewModel)

            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small
                )
            )

            DurationSection(
                viewModel = viewModel
            )

            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.medium
                )
            )

            DisableSection(
                viewModel = viewModel
            )
        }
    }

}
