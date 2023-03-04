package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.FlightsModel

data class FlightData(
    val flight_0: Flight,
    val flight_1: Flight?,
    val flight_2: Flight?,
    val flight_3: Flight?,
    val flight_4: Flight?
)

fun FlightData.toFlightDataModel(): FlightsModel =
    FlightsModel(
        listOfNotNull(
            flight_0,
            flight_1,
            flight_2,
            flight_3,
            flight_4
        ).map { it.toFlightModel() }
    )