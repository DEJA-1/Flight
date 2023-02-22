package com.example.flight.util

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.flight.domain.model.FilterParams
import com.example.flight.domain.model.flight.ItineraryModel

fun filterFlights(
    filterParams: FilterParams,
    data: List<ItineraryModel>?
): List<ItineraryModel>? {

    Log.d("TEST", filterParams.toString())

    if (filterParams.disableNextDayArrivals.value) {
        return data?.filter {
            it.priceDetails?.totalPerTicket?.toInt()!! < filterParams.maxPrice.value
                    && convertTimeToHours(it.sliceData!!.slice.info.duration) <= filterParams.duration.value
                    && checkIfArrivesNextDay(
                it.sliceData.slice.departure.datetime.date,
                it.sliceData.slice.arrival.datetime.date
            ) != filterParams.disableNextDayArrivals.value
        }
    } else {
        return data?.filter {
            it.priceDetails?.totalPerTicket?.toInt()!! < filterParams.maxPrice.value
                    && convertTimeToHours(it.sliceData!!.slice.info.duration) <= filterParams.duration.value
        }


    }

}