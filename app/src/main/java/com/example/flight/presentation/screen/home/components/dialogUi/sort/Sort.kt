package com.example.flight.presentation.screen.home.components.dialogUi.sort

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.ui.theme.spacing


@Composable
fun DialogUiSort(
    isDoneEnabled: MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel(),
    selectedSort: MutableState<String> = viewModel.selectedSort
) {

    isDoneEnabled.value = true

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(MaterialTheme.spacing.small),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn() {
            items(listOf("Price", "Departure time", "Duration")) { item ->
                SortRow(item = item, selectedSort = selectedSort) {
                    viewModel.updateSelectedSort(item)
                }
            }
        }

    }

}