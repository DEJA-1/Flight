package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.*

data class SliceModel(
    val airline: AirlineModel,
    val arrival: ArrivalModel,
    val departure: DepartureModel,
    val flightData: FlightDataModel,
    val info: InfoSliceModel
)
