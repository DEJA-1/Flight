package com.example.flight.domain.model

data class FilterParametersState(
    var maxPrice: Int = 10000,
    var maxDuration: Int = 50,
    var disableNextDayArrivals: Boolean = false
)