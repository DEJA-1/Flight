package com.example.flight.domain.model

data class FilterParametersState(
//    var maxPrice: MutableState<Int> = mutableStateOf(10000),
//    var duration: MutableState<Int> = mutableStateOf(50),
//    var disableNextDayArrivals: MutableState<Boolean> = mutableStateOf(false)
    var maxPrice: Int = 10000,
    var maxDuration: Int = 50,
    var disableNextDayArrivals: Boolean = false
)