package com.example.flight.presentation.screen.home.components

data class ButtonUiState(
    val buttonNames: List<String> = listOf("Departure", "Arrival", "Date", "Passengers"),
    val buttonNamesFilters: List<String> = listOf("Sort by", "Filter", "SAVE"),
    val selectedButtonIndex: Int = 0,
    val selectedButtonName: String = ""
)
