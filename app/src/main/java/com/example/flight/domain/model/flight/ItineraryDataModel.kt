package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.Itinerary
import com.example.flight.domain.model.FilterParams
import com.example.flight.util.filterFlights

data class ItineraryDataModel(
    val itineraries: List<ItineraryModel>
)

fun ItineraryDataModel.filterItineraries() = filterFlights(filterParams = FilterParams(), data = itineraries)
