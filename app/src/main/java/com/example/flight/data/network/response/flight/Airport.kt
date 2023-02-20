package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.AirportModel

data class Airport(
    val city: String,
    val city_id: Any,
    val code: String,
    val country: String,
    val name: String,
    val state: String
)

fun Airport.toAirportModel(): AirportModel =
    AirportModel(
        city = city,
        code = code,
        country = country,
        name = name
    )