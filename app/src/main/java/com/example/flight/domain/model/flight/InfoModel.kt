package com.example.flight.domain.model.flight
@kotlinx.serialization.Serializable
data class InfoModel(
    val aircraft: String,
    val aircraftType: String,
    val cabinClass: String,
    val cabinName: String,
    val duration: String,
    val stopCount: Int
)
