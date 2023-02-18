package com.example.flight.util

import com.example.flight.domain.model.flight.Itinerary
import com.example.flight.domain.model.flight.ItineraryData

fun convertToItineraryList(itineraryData: ItineraryData): List<Itinerary> =
    listOf(
        itineraryData.itinerary0,
        itineraryData.itinerary1,
        itineraryData.itinerary2,
        itineraryData.itinerary3,
        itineraryData.itinerary4,
        itineraryData.itinerary5,
        itineraryData.itinerary6,
        itineraryData.itinerary7,
        itineraryData.itinerary8,
        itineraryData.itinerary9,
    )