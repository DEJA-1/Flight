package com.example.flight.data.network

import com.example.flight.BuildConfig
import com.example.flight.data.network.response.flight.ApiResponse
import com.example.flight.data.network.response.location.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightApi {
    @GET(value = "v1/flights/locations")
    suspend fun getLocations(
        @Query("rapidapi-key") apiKey: String = BuildConfig.API_KEY,
        @Query("name") name : String,
    ) : LocationResponse

    @GET(value = "v2/flight/departures")
    suspend fun getFlights(
        @Query("rapidapi-key") apiKey: String = BuildConfig.API_KEY,
        @Query("departure_date") date: String,
        @Query("adults") passengers: Int,
        @Query("sid") sid: String = "SIFjfID63",
        @Query("origin_city_id") cityDep: String,
        @Query("destination_city_id") cityArr: String,
        @Query("number_of_itineraries") itinerariesCount: Int = 100
    ) : ApiResponse

}
