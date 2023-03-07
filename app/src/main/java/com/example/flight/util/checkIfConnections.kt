package com.example.flight.util

import com.example.flight.domain.model.flight.ItineraryModel

fun checkIfConnections(
    connectionCount: Int,
): Boolean {
    return connectionCount != 0
}