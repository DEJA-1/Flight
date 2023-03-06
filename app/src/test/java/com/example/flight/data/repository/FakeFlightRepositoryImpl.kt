package com.example.flight.data.repository

import com.example.flight.common.Resource
import com.example.flight.data.network.response.flight.ApiResponse
import com.example.flight.data.network.response.flight.GetAirFlightDepartures
import com.example.flight.data.network.response.flight.Results
import com.example.flight.data.network.response.location.LocationResponse
import com.example.flight.domain.repository.FlightRepository

class FakeFlightRepositoryImpl : FlightRepository {

    private var shouldReturnNetworkError = false
    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getLocation(name: String): Resource<List<LocationResponse>> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error")
        } else {
            Resource.Success(listOf(LocationResponse(cityName = "New York")))
        }
    }

    override suspend fun getFlights(
        date: String,
        cityDeparture: String,
        cityArrival: String,
        passengers: Int,
    ): Resource<ApiResponse> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Error")
        } else {
            Resource.Success(ApiResponse(GetAirFlightDepartures(Results(null, "", 0, ""))))
        }
    }
}