package com.example.flight.data.repository

import android.util.Log
import com.example.flight.common.Resource
import com.example.flight.data.network.FlightApi
import com.example.flight.data.network.response.flight.ApiResponse
import com.example.flight.data.network.response.location.LocationResponse
import com.example.flight.domain.repository.FlightLocationRepository

class FlightLocationRepositoryImpl(
    private val api: FlightApi
) : FlightLocationRepository {
    override suspend fun getLocation(name: String): Resource<List<LocationResponse>> {
        return try {
            Resource.Loading(true)
            val response = api.getLocations(name = name)
            Log.d("Response", response.toString())

            if (response.first().cityName?.isNotEmpty() == true)
                Resource.Loading(false)

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(message = "Invalid location")
        }
    }

    override suspend fun getFlights(date: String, cityDeparture: String, cityArrival: String, passengers: Int): Resource<ApiResponse> {
        return try {
            Resource.Loading(true)
            val response = api.getFlights(
                date = date,
                cityDep = cityDeparture,
                cityArr = cityArrival,
                passengers = passengers
            )
            Log.d("Response", response.toString())

            if (response.getAirFlightDepartures.results.result == null) {
                Resource.Error(message = "No results")
            } else {
                Resource.Success(response)
            }
        } catch (e: Exception) {
            Resource.Error(message = "No results found for given criteria")
        }
    }

}