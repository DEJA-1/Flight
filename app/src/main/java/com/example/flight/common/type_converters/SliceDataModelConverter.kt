package com.example.flight.common.type_converters

import androidx.room.TypeConverter
import com.example.flight.domain.model.flight.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SliceDataModelConverter {


    @TypeConverter
    fun convertToJsonString(sliceDetails: SlicesModel?): String {
        return Json.encodeToString(sliceDetails)
    }

    @TypeConverter
    fun convertToObject(json: String): SlicesModel? {
        return Json.decodeFromString(json)
    }
    
}