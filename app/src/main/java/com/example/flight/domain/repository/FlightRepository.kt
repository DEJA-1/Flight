package com.example.flight.domain.repository

import com.example.flight.common.Resource
import com.example.flight.data.network.response.flight.ApiResponse
import com.example.flight.data.network.response.location.LocationResponse

interface FlightRepository {

    suspend fun getLocation(name: String): Resource<List<LocationResponse>>
    suspend fun getFlights(date: String, cityDeparture: String, cityArrival: String, passengers: Int) : Resource<ApiResponse>
}