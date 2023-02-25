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


//    @TypeConverter
//    fun fromString(value: String): SliceDataModel {
//        val json = JSONObject(value)
//
//        val slice = json.getJSONObject("slice")
//
//        val infoSlice = slice.getJSONObject("info")
//        val airline = slice.getJSONObject("airline")
//        val arrival = slice.getJSONObject("arrival")
//        val departure = slice.getJSONObject("departure")
//        val flightData = slice.getJSONObject("flightData")
//        val infoFlight = slice.getJSONObject("info")
//
//        val airlineModel = AirlineModel(
//            airline.getString("logo"),
//            airline.getString("name")
//        )
//
//        val airportModel = AirportModel(
//            arrival.getJSONObject("airport").getString("city"),
//            arrival.getJSONObject("airport").getString("code"),
//            arrival.getJSONObject("airport").getString("country"),
//            arrival.getJSONObject("airport").getString("name")
//        )
//
//        val datetimeModel = DatetimeModel(
//            arrival.getJSONObject("datetime").getString("date"),
//            arrival.getJSONObject("datetime").getString("dateDisplay"),
//            arrival.getJSONObject("datetime").getString("time24h")
//        )
//
//        val arrivalModel = ArrivalModel(
//            airportModel,
//            datetimeModel
//        )
//
//        val departureModel = DepartureModel(
//            airportModel,
//            datetimeModel
//        )
//
//        val infoSliceModel = InfoSliceModel(
//            infoSlice.getInt("connectionCount"),
//            infoSlice.getString("duration"),
//            infoSlice.getInt("id"),
//            infoSlice.getInt("stopCount")
//        )
//
//        val infoModel = InfoModel(
//            infoFlight.getString("aircraft"),
//            infoFlight.getString("aircraftType"),
//            infoFlight.getString("cabinClass"),
//            infoFlight.getString("cabinName"),
//            infoFlight.getString("duration"),
//            infoFlight.getInt("stopCount")
//        )
//
//        val flightModel = FlightModel(
//            arrivalModel,
//            departureModel,
//            infoModel
//        )
//
//        val flightDataModel = FlightDataModel(
//            flights = listOf<FlightModel>()
//        )
//
//
//        val sliceModel = SliceModel(
//            info = infoSliceModel,
//            airline = airlineModel,
//            arrival = arrivalModel,
//            departure = departureModel,
//            flightData = flightDataModel
//        )
//
//        return SliceDataModel(sliceModel)
//    }
//
//    @TypeConverter
//    fun toString(sliceDataModel: SliceDataModel): String {
//        val slice = sliceDataModel.slice
//
//        val airline = JSONObject()
//            .put("logo", slice.airline.logo)
//            .put("name", slice.airline.name)
//
//        val arrival = JSONObject()
//            .put(
//                "airport", JSONObject()
//                    .put("city", slice.arrival.airport.city)
//                    .put("code", slice.arrival.airport.code)
//                    .put("country", slice.arrival.airport.country)
//                    .put("name", slice.arrival.airport.name)
//            )
//            .put(
//                "datetime", JSONObject()
//                    .put("date", slice.arrival.datetime.date)
//                    .put("dateDisplay", slice.arrival.datetime.dateDisplay)
//                    .put("time24h", slice.arrival.datetime.time24h)
//            )
//
//        val departure = JSONObject()
//            .put(
//                "airport", JSONObject()
//                    .put("city", slice.departure.airport.city)
//                    .put("code", slice.departure.airport.code)
//                    .put("country", slice.departure.airport.country)
//                    .put("name", slice.departure.airport.name)
//            )
//            .put(
//                "datetime", JSONObject()
//                    .put("date", slice.arrival.datetime.date)
//                    .put("dateDisplay", slice.arrival.datetime.dateDisplay)
//                    .put("time24h", slice.arrival.datetime.time24h)
//            )
//
//        val flightModel = slice.flightData.flights[0]
//        val flightInfo = flightModel.info
//
//        val infoSlice = JSONObject()
//            .put("connectionCount", slice.info.connectionCount)
//            .put("duration", slice.info.duration)
//            .put("id", slice.info.id)
//            .put("stopCount", slice.info.stopCount)
//
//        val infoFlight = JSONObject()
//            .put("aircraft", flightInfo.aircraft)
//            .put("aircraftType", flightInfo.aircraftType)
//            .put("cabinClass", flightInfo.cabinClass)
//            .put("cabinName", flightInfo.cabinName)
//            .put("duration", flightInfo.duration)
//            .put("stopCount", flightInfo.stopCount)
//            .put("connectionCount", slice.info.connectionCount)
//            .put("duration", slice.info.duration)
//            .put("id", slice.info.id)
//            .put("stopCount", slice.info.stopCount)
//
//        val sliceJson = JSONObject()
//            .put("slice", slice)
//            .put("info", infoSlice)
//            .put("airline", airline)
//            .put("arrival", arrival)
//            .put("departure", departure)
//            .put("flightData", JSONObject().put("flights", JSONArray().put(flightModel)))
//
//
//        return sliceJson.toString()
//    }
}