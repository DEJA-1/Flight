package com.example.flight.util

import com.example.flight.domain.model.flight.ItineraryModel

fun sortFlights(criteria: String, data: List<ItineraryModel>?) : List<ItineraryModel>?{
    when (criteria){
        "Departure time" -> return data?.sortedBy {it.sliceData.slice.departure.datetime.time24h}
        "Price" -> return data?.sortedBy {it.priceDetails.totalPerTicket}
        "Duration" ->  return data?.sortedBy { convertTimeToHours(it.sliceData.slice.info.duration) }
    }
    return data
}