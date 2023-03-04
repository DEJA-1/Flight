package com.example.flight.util

import com.example.flight.domain.model.flight.AirportModel
import com.example.flight.domain.model.flight.DepartureModel
import com.example.flight.domain.model.flight.ItineraryModel

// Needed because sometimes data from API about airports is missing.
fun departureCityString(airport: AirportModel) : String{
    if (airport.city == "") {
        return airport.code
    }

    return airport.city
}