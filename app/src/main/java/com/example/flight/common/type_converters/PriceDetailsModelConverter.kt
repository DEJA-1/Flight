package com.example.flight.common.type_converters

import androidx.room.TypeConverter
import com.example.flight.domain.model.flight.PriceDetailsModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PriceDetailsModelConverter {

    @TypeConverter
    fun convertToJsonString(priceDetails: PriceDetailsModel?): String {
        return Json.encodeToString(priceDetails)
    }

    @TypeConverter
    fun convertToObject(json: String): PriceDetailsModel? {
        return Json.decodeFromString(json)
    }

}
