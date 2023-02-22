package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {

   private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme = _isDarkTheme
    fun switchTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

}