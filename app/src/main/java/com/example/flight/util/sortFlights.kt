package com.example.flight.util

import com.example.flight.domain.model.flight.ItineraryModel

fun sortFlights(criteria: String, itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return when (criteria) {
        "Departure time" -> sortByDepartureTime(itineraries)
        "Price" -> sortByPrice(itineraries)
        "Duration" -> sortByDuration(itineraries)
        else -> itineraries
    }
}

private fun sortByDepartureTime(itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return itineraries?.sortedBy { it.sliceData?.slice?.departure?.datetime?.time24h }
}

private fun sortByPrice(itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return itineraries?.sortedBy { it.priceDetails?.totalPerTicket }
}

private fun sortByDuration(itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return itineraries?.sortedBy { convertTimeToHours(it.sliceData?.slice?.info?.duration.toString()) }
}