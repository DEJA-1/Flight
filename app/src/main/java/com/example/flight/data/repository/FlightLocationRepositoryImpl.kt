package com.example.flight.data.repository

import com.example.flight.common.Resource
import com.example.flight.data.network.FlightApi
import com.example.flight.domain.model.Location
import com.example.flight.domain.repository.FlightLocationRepository

class FlightLocationRepositoryImpl(
    private val api: FlightApi
) : FlightLocationRepository {
    override suspend fun getLocation(name: String) : Resource<Location>{
        return try {
            Resource.Loading(true)
            val response = api.getLocations(name = name)

            if (response.cityName?.isNotEmpty() == true)
                Resource.Loading(false)

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}