package com.example.flight.domain.model.flight

@kotlinx.serialization.Serializable
data class SliceModel(
    val info: InfoSliceModel,
    val airline: AirlineModel,
    val arrival: ArrivalModel,
    val departure: DepartureModel,
    val flightData: FlightsModel
)
