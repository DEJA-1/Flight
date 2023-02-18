package com.example.flight.domain.model.flight

data class FlightInfo(
    val id: Int,
    val cabinClass: String,
    val duration: String,
    val note: Note,
    val stopCount: Int,
    val aircraft: String
)
