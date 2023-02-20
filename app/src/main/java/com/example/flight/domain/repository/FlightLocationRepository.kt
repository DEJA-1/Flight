package com.example.flight.domain.repository

import com.example.flight.common.Resource
import com.example.flight.data.network.response.flight.ApiResponse2
import com.example.flight.domain.model.Location

interface FlightLocationRepository {

    suspend fun getLocation(name: String): Resource<List<Location>>
    suspend fun getFlights(date: String, cityDep: String, cityArr: String, passengers: Int) : Resource<ApiResponse2>
}