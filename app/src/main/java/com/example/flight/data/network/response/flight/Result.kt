package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.ResultModel

data class Result(
    val airline_data: AirlineData,
    val airport_data: AirportData,
    val branding_data: BrandingData,
    val cabin_restrictions: CabinRestrictions,
    val itinerary_count: Int,
    val itinerary_data: ItineraryData,
    val nearby_airports: NearbyAirports,
    val page_number: String,
    val search_data: SearchData,
    val search_type: String,
    val sid: String
)

fun Result.toResultModel(): ResultModel =
    ResultModel(
//        airportData = airport_data,
//        airlineData = airline_data,
        itineraryCount = itinerary_count,
        itineraryData = itinerary_data
    )