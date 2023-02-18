package com.example.flight.data.network.response.flight

data class Origin(
    val city: String,
    val code: String,
    val country: String,
    val isAirport: Boolean,
    val name: String,
    val state: String
)