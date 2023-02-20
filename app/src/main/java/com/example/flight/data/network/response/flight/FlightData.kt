package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.FlightDataModel

data class FlightData(
    val flight_0: Flight,
    val flight_1: Flight?
)

fun FlightData.toFlightDataModel(): FlightDataModel =
    FlightDataModel(
        flight0 = flight_0.toFlightModel(),
        flight1 = flight_1?.toFlightModel()
    )