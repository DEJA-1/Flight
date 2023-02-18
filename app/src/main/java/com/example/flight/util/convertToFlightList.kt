package com.example.flight.util

import com.example.flight.domain.model.flight.Flight
import com.example.flight.domain.model.flight.FlightData

fun convertToFlightList(flight: FlightData) =
    listOf(
        flight.flight0,
        flight.flight1,
        flight.flight2,
        flight.flight3,
        flight.flight4,
        flight.flight5,
        flight.flight6,
        flight.flight7,
        flight.flight8,
        flight.flight9,
        flight.flight10,
        flight.flight11,
        flight.flight12,
        flight.flight13,
        flight.flight14,
        flight.flight15,
        flight.flight16,
        flight.flight17,
        flight.flight18,
        flight.flight19,
        flight.flight20,
        flight.flight21,
        flight.flight22,
        flight.flight23,
        flight.flight24,
        flight.flight25,
        flight.flight26,
        flight.flight27,
        flight.flight28,
        flight.flight29
    ).map {
        it != null
    }