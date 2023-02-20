package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.Arrival
import com.example.flight.data.network.response.flight.Departure
import com.example.flight.data.network.response.flight.Info

data class FlightModel(
    val arrival: ArrivalModel,
    val departure: DepartureModel,
    val info: InfoModel
)
