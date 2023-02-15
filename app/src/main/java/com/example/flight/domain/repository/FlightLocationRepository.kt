package com.example.flight.domain.repository

import com.example.flight.common.Resource
import com.example.flight.domain.model.Location

interface FlightLocationRepository {

    suspend fun getLocation(name: String): Resource<Location>
}