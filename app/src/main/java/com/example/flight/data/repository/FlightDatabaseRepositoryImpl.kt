package com.example.flight.data.repository

import com.example.flight.data.database.FlightDao
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FlightDatabaseRepositoryImpl @Inject constructor(
    private val dao: FlightDao
): FlightDatabaseRepository {
    override suspend fun addItineraryToDb(itinerary: ItineraryModel) {
        dao.addItineraryToDb(itinerary)
    }

    override suspend fun deleteItineraryFromDb(itinerary: ItineraryModel) {
        dao.deleteItineraryFromDb(itinerary)
    }

    override suspend fun getAllItinerariesFromDb() : Flow<List<ItineraryModel>> =
        dao.getAllItinerariesFromDb().flowOn(Dispatchers.IO).conflate()

}