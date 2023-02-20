package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.InfoSliceModel

data class InfoSlice(
    val connection_count: Int,
    val duration: String,
    val id: Int,
    val max_connection_duration: Any,
    val max_duration: Any,
    val max_stops: Any,
    val notes: Any,
    val stop_count: Int
)

fun InfoSlice.toInfoSliceModel(): InfoSliceModel =
    InfoSliceModel(
        connectionCount = connection_count,
        duration = duration,
        id = id,
        stopCount = stop_count
    )