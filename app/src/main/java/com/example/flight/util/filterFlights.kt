package com.example.flight.util

import android.util.Log
import com.example.flight.domain.model.FilterParametersState
import com.example.flight.domain.model.flight.ItineraryModel

//fun filterFlights(
//    filterParametersState: FilterParametersState,
//    itineraries: List<ItineraryModel>?
//): List<ItineraryModel>? {
//
//    if (filterParametersState.disableNextDayArrivals) {
//        return itineraries?.filter {
//            it.priceDetails?.totalPerTicket?.toInt()!! < filterParametersState.maxPrice
//                    && convertTimeToHours(it.sliceData!!.slice.info.duration) <= filterParametersState.maxDuration
//                    && checkIfArrivesNextDay(
//                it.sliceData.slice.departure.datetime.date,
//                it.sliceData.slice.arrival.datetime.date
//            ) != filterParametersState.disableNextDayArrivals
//        }
//    } else {
//        return itineraries?.filter {
//            it.priceDetails?.totalPerTicket?.toInt()!! < filterParametersState.maxPrice
//                    && convertTimeToHours(it.sliceData!!.slice.info.duration) <= filterParametersState.maxDuration
//        }
//
//    }
//
//}

fun filterFlights(
    filterParametersState: FilterParametersState,
    itineraries: List<ItineraryModel>?
): List<ItineraryModel>? {
    val filteredByPrice = filterByPrice(filterParametersState.maxPrice, itineraries)
    val filteredByDuration = filterByDuration(filterParametersState.maxDuration, filteredByPrice)
    return filterByNextDayArrivals(filterParametersState.disableNextDayArrivals, filteredByDuration)
}

fun filterByPrice(maxPrice: Int, itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return itineraries?.filter {
        it.priceDetails?.totalPerTicket?.toInt()!! < maxPrice
    }
}

fun filterByDuration(maxDuration: Int, itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return itineraries?.filter {
        convertTimeToHours(it.sliceData!!.slice.info.duration) <= maxDuration
    }
}

fun filterByNextDayArrivals(disableNextDayArrivals: Boolean, itineraries: List<ItineraryModel>?): List<ItineraryModel>? {
    return itineraries?.filter {
        if (disableNextDayArrivals) {
            checkIfArrivesNextDay(
                it.sliceData!!.slice.departure.datetime.date,
                it.sliceData.slice.arrival.datetime.date
            ) != disableNextDayArrivals
        } else {
            true
        }
    }
}