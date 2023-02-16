package com.example.flight.domain.model

data class FlightParams(
    var departureTime: String = "",
    var locationDeparture: String = "",
    var locationArrival: String = "",
    var passengers: Int = 1,
)
