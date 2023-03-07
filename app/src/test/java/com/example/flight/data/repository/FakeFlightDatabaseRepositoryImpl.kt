package com.example.flight.data.repository

import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeFlightDatabaseRepositoryImpl : FlightDatabaseRepository{

    private val itineraries = mutableListOf<ItineraryModel>()

    private val allItineraries = MutableStateFlow(itineraries)

    override suspend fun insertItineraryToDb(itinerary: ItineraryModel) {
        itineraries.add(itinerary)
        allItineraries.value = itineraries
    }

    override suspend fun deleteItineraryFromDb(itinerary: ItineraryModel) {
        itineraries.remove(itinerary)
        allItineraries.value = itineraries
    }

    override suspend fun getAllItinerariesFromDb(): Flow<List<ItineraryModel>> {
        return allItineraries
    }
}