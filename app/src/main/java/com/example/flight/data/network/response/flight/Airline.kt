package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.AirlineModel

data class Airline(
    val airline_count: Int,
    val code: String,
    val logo: String,
    val name: String
)

fun Airline.toAirlineModel(): AirlineModel =
    AirlineModel(
        logo = logo,
        name = name
    )