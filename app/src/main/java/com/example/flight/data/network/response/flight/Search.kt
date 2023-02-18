package com.example.flight.data.network.response.flight

data class Search(
    val departure_date: String,
    val destination: Destination,
    val origin: Origin
)