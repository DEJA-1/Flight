package com.example.flight.data.network.response.locations

data class Result(
    val airline_data: AirlineData,
    val airport_data: AirportData,
    val itinerary_count: Int,
    val search_data: SearchData,
    val search_type: String,
    val sid: String
)