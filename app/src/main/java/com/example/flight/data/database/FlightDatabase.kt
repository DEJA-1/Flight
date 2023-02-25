package com.example.flight.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flight.common.type_converters.PriceDetailsModelConverter
import com.example.flight.common.type_converters.SliceDataModelConverter
import com.example.flight.domain.model.flight.ItineraryModel

@Database(entities = [ItineraryModel::class], version = 1, exportSchema = false)
@TypeConverters(PriceDetailsModelConverter::class, SliceDataModelConverter::class)
abstract class FlightDatabase : RoomDatabase() {

    abstract val flightDao: FlightDao

    companion object {
        const val DATABASE_NAME = "itinerary_table"
    }
}