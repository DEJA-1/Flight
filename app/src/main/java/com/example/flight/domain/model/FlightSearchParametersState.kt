package com.example.flight.domain.model

import java.time.LocalDate

data class FlightSearchParametersState(
    var departureTime: String = "${LocalDate.now().plusDays(4)}",
    var locationDeparture: String? = "WAW",
    var locationArrival: String? = "PAR",
    var passengers: Int = 1,
)
