package com.example.flight.data.repository

import android.util.Log
import com.example.flight.common.Resource
import com.example.flight.data.network.FlightApi
import com.example.flight.data.network.response.flight.Test2
import com.example.flight.data.network.response.locations.Test
import com.example.flight.domain.model.Location
import com.example.flight.domain.repository.FlightLocationRepository

class FlightLocationRepositoryImpl(
    private val api: FlightApi
) : FlightLocationRepository {
    override suspend fun getLocation(name: String) : Resource<List<Location>>{
        return try {
            Resource.Loading(true)
            val response = api.getLocations(name = name)
            Log.d("Response", response.toString())


            if (response.first().cityName?.isNotEmpty() == true)
                Resource.Loading(false)

            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    override suspend fun getFlights(
        date: String,
        cityDep: String,
        cityArr: String,
        passengers: Int
    ): Resource<Test2> {
        return try {
            Resource.Loading(true)
            val response = api.getFlights(date = date, cityDep = cityDep, cityArr = cityArr, passengers = passengers)
            Log.d("Response", response.toString())

            if (response.getAirFlightDepartures != null)
                Resource.Loading(false)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}