package com.example.flight.domain.model.flight

import androidx.room.*
import com.example.flight.common.type_converters.PriceDetailsModelConverter
import com.example.flight.common.type_converters.SliceDataModelConverter

@kotlinx.serialization.Serializable
@Entity(tableName = "itinerary_table")
data class ItineraryModel(
    @PrimaryKey(autoGenerate = true)
    val itineraryId: Int = 0,

    @ColumnInfo(name = "price_details")
    @TypeConverters(PriceDetailsModelConverter::class)
    val priceDetails: PriceDetailsModel? = null,

    @ColumnInfo(name = "slice_data")
    @TypeConverters(SliceDataModelConverter::class)
    val sliceData: SlicesModel? = null
)
