package com.example.flight.domain.model.flight
@kotlinx.serialization.Serializable
data class InfoSliceModel(
    val connectionCount: Int,
    val duration: String,
    val id: Int,
    val stopCount: Int
)
