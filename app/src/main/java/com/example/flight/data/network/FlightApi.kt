package com.example.flight.data.network

import android.content.pm.ApplicationInfo
import com.example.flight.BuildConfig
import com.example.flight.domain.model.Location
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.FileInputStream
import java.util.Properties

interface FlightApi {
    @GET(value = "v1/flights/locations")
    suspend fun getLocations(
        @Query("rapidapi-key") apiKey: String = BuildConfig.API_KEY,
        @Query("name") name : String,
    ) : List<Location>

}
