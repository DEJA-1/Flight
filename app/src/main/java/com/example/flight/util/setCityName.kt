package com.example.flight.util

import com.example.flight.domain.model.flight.AirportModel

// Needed because sometimes data from API about airports is missing.
fun setCityName(airport: AirportModel) : String{
    if (airport.city == "") {
        return airport.code
    }

    return airport.city
}