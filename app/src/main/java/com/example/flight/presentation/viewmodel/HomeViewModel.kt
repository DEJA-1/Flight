package com.example.flight.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val buttonList = listOf<String>("Departure", "Arrival", "Date", "Passengers")
    val buttonListFilter = listOf<String>("Sort by", "Filter")

    private val _selectedButtonIndex = mutableStateOf(0)
    val selectedButtonIndex = _selectedButtonIndex

    fun updateSelectedButtonIndex(index: Int) {
        _selectedButtonIndex.value = index
    }
}