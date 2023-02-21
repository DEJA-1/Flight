package com.example.flight.presentation.screen.home.components.dialogUi.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.presentation.screen.home.components.ParamsSection
import com.example.flight.presentation.viewModel.HomeViewModel

@Composable
fun DurationSection(
    viewModel: HomeViewModel
) {

    val selectedButtonIndex = remember {
        mutableStateOf(-1)
    }

    ParamsSection(
        buttonList = listOf("<5h", "<15h", "<30h", "<50h"),
        isFilter = false,
        selectedButtonIndex = selectedButtonIndex,
    ) { index, name ->
        selectedButtonIndex.value = index
        viewModel.updateSelectedDurationFilter(name)
    }
}