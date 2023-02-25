package com.example.flight.domain.repository

import com.example.flight.domain.model.flight.ItineraryModel
import kotlinx.coroutines.flow.Flow

interface FlightDatabaseRepository {

    suspend fun addItineraryToDb(itinerary: ItineraryModel)

    suspend fun deleteItineraryFromDb(itinerary: ItineraryModel)

    suspend fun getAllItinerariesFromDb() : Flow<List<ItineraryModel>>
}