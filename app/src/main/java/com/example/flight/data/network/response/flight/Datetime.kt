package com.example.flight.data.network.response.flight

data class Datetime(
    val date: String,
    val date_display: String,
    val date_time: String,
    val time_12h: String,
    val time_24h: String,
    val time_window: List<Any>? = null
)