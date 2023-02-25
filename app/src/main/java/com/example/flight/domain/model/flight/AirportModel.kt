package com.example.flight.domain.model.flight
@kotlinx.serialization.Serializable
data class AirportModel(
    val city: String,
    val code: String,
    val country: String,
    val name: String,
)
