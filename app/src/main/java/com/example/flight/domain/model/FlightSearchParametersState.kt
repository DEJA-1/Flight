package com.example.flight.domain.model

import java.time.LocalDate

data class FlightSearchParametersState(
    val departureTime: String = "${LocalDate.now().plusDays(4)}",
    val locationDeparture: String? = "WAW",
    val locationArrival: String? = "PAR",
    val passengers: Int = 1,
)
