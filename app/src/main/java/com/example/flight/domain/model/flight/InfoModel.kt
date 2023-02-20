package com.example.flight.domain.model.flight

data class InfoModel(
    val aircraft: String,
    val aircraftType: String,
    val cabinClass: String,
    val cabinName: String,
    val duration: String,
    val stopCount: Int
)
