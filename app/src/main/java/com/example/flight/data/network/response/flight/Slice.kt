package com.example.flight.data.network.response.flight

data class Slice(
    val airline: Airline,
    val arrival: Arrival,
    val departure: Departure,
    val flight_data: FlightData,
    val info: Info
)