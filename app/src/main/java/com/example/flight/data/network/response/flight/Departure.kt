package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.DepartureModel

data class Departure(
    val airport: Airport,
    val datetime: Datetime
)

fun Departure.toDepartureModel(): DepartureModel =
    DepartureModel(
        airport = airport.toAirportModel(),
        datetime = datetime.toDatetimeModel()
    )