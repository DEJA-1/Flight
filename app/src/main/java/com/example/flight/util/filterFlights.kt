package com.example.flight.util

import android.util.Log
import com.example.flight.domain.model.FilterParametersState
import com.example.flight.domain.model.flight.ItineraryModel

fun filterFlights(
    filterParams: FilterParametersState,
    data: List<ItineraryModel>?
): List<ItineraryModel>? {

    Log.d("TEST", filterParams.toString())

    if (filterParams.disableNextDayArrivals) {
        return data?.filter {
            it.priceDetails?.totalPerTicket?.toInt()!! < filterParams.maxPrice
                    && convertTimeToHours(it.sliceData!!.slice.info.duration) <= filterParams.maxDuration
                    && checkIfArrivesNextDay(
                it.sliceData.slice.departure.datetime.date,
                it.sliceData.slice.arrival.datetime.date
            ) != filterParams.disableNextDayArrivals
        }
    } else {
        return data?.filter {
            it.priceDetails?.totalPerTicket?.toInt()!! < filterParams.maxPrice
                    && convertTimeToHours(it.sliceData!!.slice.info.duration) <= filterParams.maxDuration
        }


    }

}