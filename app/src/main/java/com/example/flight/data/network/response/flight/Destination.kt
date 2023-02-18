package com.example.flight.data.network.response.flight

data class Destination(
    val city: String,
    val code: String,
    val country: String,
    val isAirport: Boolean,
    val name: String,
    val state: Any
)