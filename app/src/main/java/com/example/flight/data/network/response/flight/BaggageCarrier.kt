package com.example.flight.data.network.response.flight

data class BaggageCarrier(
    val airline: String,
    val arrival: String,
    val available: String,
    val departure: String,
    val popup_url: String
)