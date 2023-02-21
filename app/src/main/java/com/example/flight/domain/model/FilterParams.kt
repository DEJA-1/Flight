package com.example.flight.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FilterParams(
    var maxPrice: MutableState<Int> = mutableStateOf(10000),
    var duration: MutableState<Int> = mutableStateOf(50),
    var disableNextDayArrivals: MutableState<Boolean> = mutableStateOf(false)
)