package com.example.flight.common.type_converters

import androidx.room.TypeConverter
import com.example.flight.data.network.response.flight.InfoSlice
import com.example.flight.domain.model.flight.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject

class SliceDataModelConverter {


    @TypeConverter
    fun convertToJsonString(sliceDetails: SliceDataModel?): String {
        return Json.encodeToString(sliceDetails)
    }

    @TypeConverter
    fun convertToObject(json: String): SliceDataModel? {
        return Json.decodeFromString(json)
    }

}