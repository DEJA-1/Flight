package com.example.flight.util

import com.example.flight.domain.model.flight.ItineraryModel

fun checkIfConnections(
    data: ItineraryModel
): Boolean {
    return data.sliceData!!.slice.info.connectionCount != 0
}