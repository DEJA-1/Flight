package com.example.flight.data.local.database

import androidx.room.*
import com.example.flight.domain.model.flight.ItineraryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItineraryToDb(itinerary: ItineraryModel)
    @Delete
    suspend fun deleteItineraryFromDb(itinerary: ItineraryModel)
    @Query("SELECT * FROM itinerary_table")
    fun getAllItinerariesFromDb() : Flow<List<ItineraryModel>>
}
