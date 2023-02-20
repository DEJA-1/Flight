package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.FlightModel

data class Flight(
    val arrival: Arrival,
    val departure: Departure,
    val info: Info
)

fun Flight.toFlightModel(): FlightModel =
  FlightModel(
      arrival = arrival.toArrivalModel(),
      departure = departure.toDepartureModel(),
      info = info.toInfoModel()
  )