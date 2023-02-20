package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.ArrivalModel

data class Arrival(
    val airport: Airport,
    val datetime: Datetime
)

fun Arrival.toArrivalModel() : ArrivalModel =
    ArrivalModel(
        airport = airport.toAirportModel(),
        datetime = datetime.toDatetimeModel()
    )