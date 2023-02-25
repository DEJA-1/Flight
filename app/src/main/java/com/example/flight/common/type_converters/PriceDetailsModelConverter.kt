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

//    @TypeConverter
//    fun fromString(value: String): PriceDetailsModel? {
//        val parts = value.split(",")
//        if (parts.size != 2) {
//            return null
//        }
//        val totalPerTicket = parts[0].toDoubleOrNull() ?: return null
//        val total = parts[1].toDoubleOrNull() ?: return null
//        return PriceDetailsModel(totalPerTicket, total)
//    }
//
//    @TypeConverter
//    fun toString(priceDetailsModel: PriceDetailsModel?): String {
//        return priceDetailsModel?.let {
//            "${it.totalPerTicket},${it.total}"
//        } ?: ""
//    }
}
