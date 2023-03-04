package com.example.flight.util

import com.example.flight.domain.model.flight.ItineraryModel

fun arrivalCityString(itinerary: ItineraryModel) : String {
    if (itinerary.sliceData!!.slice.arrival.airport.city == "") {
        return itinerary.sliceData.slice.arrival.airport.code
    }

    return itinerary.sliceData.slice.arrival.airport.city

}