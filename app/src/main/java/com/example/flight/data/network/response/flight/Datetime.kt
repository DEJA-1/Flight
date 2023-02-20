package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.DatetimeModel

data class Datetime(
    val date: String,
    val date_display: String,
    val date_time: String,
    val time_12h: String,
    val time_24h: String,
    val time_window: List<Any>? = null
)

fun Datetime.toDatetimeModel(): DatetimeModel =
    DatetimeModel(
        date = date,
        time24h = time_24h,
        dateDisplay = date_display
    )