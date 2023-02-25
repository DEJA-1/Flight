package com.example.flight.domain.model.flight
@kotlinx.serialization.Serializable
data class DepartureModel(
    val airport: AirportModel,
    val datetime: DatetimeModel
)
