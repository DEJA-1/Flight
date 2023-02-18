package com.example.flight.domain.model.flight


data class Slice(
    val airline: Airline,
    val arrival: Arrival,
    val departure: Departure,
    val flightData: FlightData,
    val info: Info
)
