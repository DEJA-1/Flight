package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.*
@kotlinx.serialization.Serializable
data class SliceModel(
    val info: InfoSliceModel,
    val airline: AirlineModel,
    val arrival: ArrivalModel,
    val departure: DepartureModel,
    val flightData: FlightDataModel
)
