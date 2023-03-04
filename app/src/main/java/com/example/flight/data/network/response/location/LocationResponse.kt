package com.example.flight.data.network.response.location

import com.example.flight.domain.model.location.Location

data class LocationResponse(
    val airportCode: Any? = null,
    val cityCode: String? = null,
    val cityID: String? = null,
    val cityName: String? = null,
    val country: String? = null,
    val countryCode: String? = null,
    val displayName: String? = null,
    val entered: String? = null,
    val fromSavedSearch: Boolean? = null,
    val gmtOffset: String? = null,
    val id: String? = null,
    val idWithType: String? = null,
    val itemName: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val poiCategoryTypeId: Int? = null,
    val provinceName: Any? = null,
    val proximity: Double? = null,
    val rank: Double? = null,
    val score: Double? = null,
    val stateCode: String? = null,
    val timeZoneID: Int? = null,
    val timeZoneName: String? = null,
    val type: String? = null,
)

fun LocationResponse.toLocation(): Location {
    return Location(
        airportCode = airportCode,
        cityCode = cityCode,
        cityID = cityID,
        cityName = cityName,
        country = country,
        countryCode = countryCode
    )
}