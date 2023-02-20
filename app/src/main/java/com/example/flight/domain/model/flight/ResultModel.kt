package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.AirlineData
import com.example.flight.data.network.response.flight.AirportData
import com.example.flight.data.network.response.flight.ItineraryData

data class ResultModel(
//    val airlineData: AirlineData,
//    val airportData: AirportData,
    val itineraryCount: Int,
    val itineraryData: ItineraryData
)
