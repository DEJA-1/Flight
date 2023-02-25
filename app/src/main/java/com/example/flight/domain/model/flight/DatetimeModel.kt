package com.example.flight.domain.model.flight
@kotlinx.serialization.Serializable
data class DatetimeModel(
    val date: String,
    val dateDisplay: String,
    val time24h: String,
)
