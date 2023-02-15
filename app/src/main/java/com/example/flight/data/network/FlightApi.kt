package com.example.flight.data.network

import com.example.flight.common.Constants.API_KEY
import com.example.flight.common.Resource
import com.example.flight.domain.model.Location
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FlightApi {

    @GET(value = "v1/flights/locations")
    suspend fun getLocations(
        @Query("rapidapi-key") apiKey: String = API_KEY,
        @Query("name") name : String,
    ) : Location

}