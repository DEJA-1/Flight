package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.SliceModel

data class Slice(
    val airline: Airline,
    val arrival: Arrival,
    val departure: Departure,
    val flight_data: FlightData,
    val info: InfoSlice
)

fun Slice.toSliceModel(): SliceModel =
    SliceModel(
        airline = airline.toAirlineModel(),
        arrival = arrival.toArrivalModel(),
        departure = departure.toDepartureModel(),
        flightData = flight_data.toFlightDataModel(),
        info = info.toInfoSliceModel()
    )